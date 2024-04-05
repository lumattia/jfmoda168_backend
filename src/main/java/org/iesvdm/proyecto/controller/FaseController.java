package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.model.Fase;
import org.iesvdm.proyecto.service.FaseService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/fase")
public class FaseController {

    private final FaseService faseService;

    public FaseController(FaseService faseService) {
        this.faseService = faseService;
    }

    @GetMapping("/{id}")
    public Fase one(@PathVariable("id") long id) {
        return this.faseService.one(id);
    }
}
