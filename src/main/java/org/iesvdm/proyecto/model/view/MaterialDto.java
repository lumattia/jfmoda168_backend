package org.iesvdm.proyecto.model.view;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MaterialDto {
    private Long id;
    private String name;
    private List<ColorDto> colors;
}
