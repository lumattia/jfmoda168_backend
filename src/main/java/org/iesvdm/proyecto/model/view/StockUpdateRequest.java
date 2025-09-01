package org.iesvdm.proyecto.model.view;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class StockUpdateRequest {
    private LocalDate date;
    private List<StockItem> stock;

    @Data
    @AllArgsConstructor
    public static class StockItem {
        private long id;
        private int amount;
    }
}