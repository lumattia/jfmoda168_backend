package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.model.entity.Product;
import org.iesvdm.proyecto.model.view.IdName;
import org.iesvdm.proyecto.model.view.PageProductDto;
import org.iesvdm.proyecto.model.view.ProductDto;
import org.iesvdm.proyecto.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping(value = {"","/"})
    public List<IdName> getAll() {
        return productService.getAll();
    }
    @GetMapping({"/page"})
    public Page<PageProductDto> all(@RequestParam(value = "code",defaultValue = "") String code,
                                    Pageable pageable) {
        return this.productService.allByFilter(code,pageable);
    }
    @PostMapping(value = {"","/"})
    public Product save(
            @RequestBody ProductDto dto) {
        return productService.saveOrUpdate(dto);
    }

    @GetMapping("/{id}")
    public Product one(@PathVariable("id") long id) {
        return this.productService.get(id);
    }
}
