package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.model.view.StockUpdateRequest;
import org.iesvdm.proyecto.service.ColorService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/api/colors")
public class ColorController {
    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @PostMapping("/add")
    public void add(@RequestBody StockUpdateRequest request) {
            colorService.add(request);
    }

    @PostMapping("/remove")
    public void remove(@RequestBody StockUpdateRequest request) {
            colorService.remove(request);
    }
}
