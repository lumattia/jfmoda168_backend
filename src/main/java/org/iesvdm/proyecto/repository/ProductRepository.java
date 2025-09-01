package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.model.entity.Product;
import org.iesvdm.proyecto.model.view.IdName;
import org.iesvdm.proyecto.model.view.PageProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> searchProductsByCodeContainingIgnoreCase(@Param("code") String code);
    @Query("SELECT p.id AS id, p.code AS name FROM Product p")
    List<IdName> findAllIdName();
    // 获取某个产品的材料列表
    @Query("SELECT m.id AS id, m.name AS name FROM Material m WHERE m.product.id = :productId")
    List<IdName> findMaterialsByProductId(@Param("productId") long productId);

    // 获取某个材料的颜色列表
    @Query("SELECT c.id AS id, c.name AS name FROM Color c WHERE c.material.id = :materialId")
    List<IdName> findColorsByMaterialId(@Param("materialId") long materialId);
}