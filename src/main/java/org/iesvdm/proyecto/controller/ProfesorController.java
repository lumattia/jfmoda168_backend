package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.domain.Profesor;
import org.iesvdm.proyecto.service.ProfesorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/profesores")
public class ProfesorController {
    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping({"","/"})
    public List<Profesor> all() {
        log.info("Accediendo a todas los profesores");
        return this.profesorService.all();
    }
    @PostMapping({"","/"})
    public Profesor save(@RequestBody Profesor profesor) {
        log.info("Guardando un profesor");
        return this.profesorService.save(profesor);
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
