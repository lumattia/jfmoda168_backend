package org.iesvdm.proyecto.model.view;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class LogFilter {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Boolean add;
    private String colorName;
    private String materialName;
    private String productCode;
}