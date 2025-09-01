package org.iesvdm.proyecto.model.view;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class PageProductDto {
    private Long id;
    private String code;
    private String name;
    private Set<String> materials;
    private Set<String> colors;
    private int totalStock;
}
