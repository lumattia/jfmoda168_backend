package org.iesvdm.proyecto.model.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.iesvdm.proyecto.model.entity.Log;

import java.time.LocalDate;


@Data
@AllArgsConstructor
public class LogPageDto {
    private long Id;
    private LocalDate logDate;
    private Log.Action action;
    private int amount;
    private String productName;
    private String materialName;
    private String colorName;
    private String imageUrl;
}