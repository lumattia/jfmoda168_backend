package org.iesvdm.proyecto.model.view;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogStatDto {
    private int amount;
    private String productName;
    private String materialName;
    private String colorName;
    private String imageUrl;
}