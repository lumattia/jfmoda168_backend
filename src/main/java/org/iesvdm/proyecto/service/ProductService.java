package org.iesvdm.proyecto.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.model.entity.*;
import org.iesvdm.proyecto.model.view.*;
import org.iesvdm.proyecto.repository.ColorRepository;
import org.iesvdm.proyecto.repository.MaterialRepository;
import org.iesvdm.proyecto.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @PersistenceContext
    EntityManager em;
    private final ProductRepository productRepository;

    private final MaterialRepository materialRepository;
    private final ColorRepository colorRepository;
    public ProductService(ProductRepository productRepository, MaterialRepository materialRepository, ColorRepository colorRepository) {
        this.productRepository = productRepository;
        this.materialRepository = materialRepository;
        this.colorRepository = colorRepository;
    }
    @Transactional
    public Product saveOrUpdate(ProductDto dto) {
        Product product;
        String uploadDir = "upload/images/"; // 文件保存目录

        // 判断新增还是修改
        if (dto.getId() != null) {
            product = productRepository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("未找到产品"));
        } else {
            product = new Product();
        }

        product.setCode(dto.getCode());
        product.setName(dto.getName());

        // 处理 Material
        if (dto.getMaterials() != null) {
            List<Material> materials = new ArrayList<>();

            for (MaterialDto mDto : dto.getMaterials()) {
                Material material;

                if (mDto.getId() != null) { // 修改
                    material = materialRepository.findById(mDto.getId())
                            .orElseThrow(() -> new RuntimeException("未找到布料"));
                    material.setName(mDto.getName());
                } else { // 新增
                    material = new Material();
                    material.setName(mDto.getName());
                    material.setProduct(product);
                }

                // 处理 Color
                List<Color> colors = new ArrayList<>();
                if (mDto.getColors() != null) {
                    for (ColorDto cDto : mDto.getColors()) {
                        Color color;

                        if (cDto.getId() != null) { // 修改
                            color = colorRepository.findById(cDto.getId())
                                    .orElseThrow(() -> new RuntimeException("未找到颜色"));
                            color.setName(cDto.getName());
                            color.setStock(cDto.getStock());
                        } else { // 新增
                            color = new Color();
                            color.setName(cDto.getName());
                            color.setStock(cDto.getStock());
                            color.setMaterial(material);
                        }

                        // 图片处理
                        if (cDto.getFileBase64() != null && !cDto.getFileBase64().isEmpty()) {
                            try {
                                File dir = new File(uploadDir);
                                if (!dir.exists()) dir.mkdirs();

                                String base64 = cDto.getFileBase64().split(",")[1];
                                byte[] bytes = Base64.getDecoder().decode(base64);
                                String filename = dto.getCode() + "_" + mDto.getName() + "_" + cDto.getName() + "_" + cDto.getFilename();
                                Path path = Paths.get(uploadDir, filename);
                                Files.write(path, bytes);
                                color.setPhotoPath("/images/" + filename);
                            } catch (IOException e) {
                                throw new RuntimeException("保存图片失败: " + cDto.getFilename(), e);
                            }
                        }

                        colors.add(color);
                    }
                }
                material.setColors(colors); // 使用 List
                materials.add(material);
            }

            product.setMaterials(materials); // 使用 List
        }

        return productRepository.save(product);
    }

    public Page<PageProductDto> allByFilter(String code, Pageable pageable){
            // Trae todos los productos filtrados por code (sin DTO todavía)
            List<Product> products = productRepository.searchProductsByCodeContainingIgnoreCase(code);

            // Mapear a DTO con totalStock
            List<PageProductDto> dtos = products.stream().map(p -> {
                Set<String> mList = p.getMaterials().stream()
                        .map(Material::getName)
                        .collect(Collectors.toSet());

                Set<String> cList = p.getMaterials().stream()
                        .flatMap(m -> m.getColors().stream())
                        .map(Color::getName)
                        .collect(Collectors.toSet());

                int totalStock = p.getMaterials().stream()
                        .flatMap(m -> m.getColors().stream())
                        .mapToInt(Color::getStock)
                        .sum();

                return new PageProductDto(
                        p.getId(),
                        p.getCode(),
                        p.getName(),
                        mList,
                        cList,
                        totalStock
                );
            }).collect(Collectors.toList());

            // Ordenar si pageable tiene sort por totalStock
        if (pageable.getSort().isSorted()) {
            for (Sort.Order order : pageable.getSort()) {
                dtos.sort((a, b) -> {
                    int cmp = 0;
                    switch (order.getProperty()) {
                        case "totalStock":
                            cmp = Integer.compare(a.getTotalStock(), b.getTotalStock());
                            break;
                        case "name":
                            cmp = a.getName().compareToIgnoreCase(b.getName());
                            break;
                        case "code":
                            cmp = a.getCode().compareToIgnoreCase(b.getCode());
                            break;
                    }
                    return order.isAscending() ? cmp : -cmp;
                });
            }
        }

            // Paginar en memoria
            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), dtos.size());
            List<PageProductDto> pageContent = start > end ? Collections.emptyList() : dtos.subList(start, end);

            return new PageImpl<>(pageContent, pageable, dtos.size());
        }
    public List<IdName> getAll(){
        return this.productRepository.findAllIdName();
    }

    public Product replace(long id, Product aula) {
        Product a= get(id);
        if (id== aula.getId()){
            return this.productRepository.save(aula);
        }
        return null;
    }

    public Product get(long id){
        Product a=this.productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("未找到产品"));
        return a;
    }
}
