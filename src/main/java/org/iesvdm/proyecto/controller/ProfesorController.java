package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.domain.Estudiante;
import org.iesvdm.proyecto.domain.Profesor;
import org.iesvdm.proyecto.service.ProfesorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/profesores")
public class ProfesorController {
    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }
    @GetMapping(value = {"","/"},params = {"!buscar"})
    public Page<Profesor> all(Pageable pageable) {
        log.info("Accediendo a todos los profesores");
        return this.profesorService.all(pageable);
    }
    @GetMapping({"","/"})
    public Page<Profesor> all(@RequestParam("buscar") String buscar,
                                Pageable pageable) {
        log.info("Accediendo a todos los profesores");
        return this.profesorService.allByFilter(buscar,pageable);
    }
    @GetMapping("/{id}")
    public Profesor one(@PathVariable("id") long id) {
        return this.profesorService.one(id);
    }
    @PutMapping("/{id}")
    public Profesor replace(@PathVariable("id") long id, @RequestBody Profesor profesor) {
        return this.profesorService.replace(id, profesor);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        this.profesorService.delete(id);
    }
}
