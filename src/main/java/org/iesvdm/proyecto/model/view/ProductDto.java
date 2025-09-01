package org.iesvdm.proyecto.model.view;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String code;
    private String name;
    private List<MaterialDto> materials;
}
