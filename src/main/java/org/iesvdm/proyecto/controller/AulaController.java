package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.model.*;
import org.iesvdm.proyecto.service.AulaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/aulas")
public class AulaController {

    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }
    @PostMapping({"","/"})
    public Aula save(@RequestBody Aula aula) {
        log.info("Guardando una aula");
        return this.aulaService.save(aula);
    }
    @GetMapping("/{id}")
    public Aula one(@PathVariable("id") long id) {
        return this.aulaService.one(id);
    }
    @PutMapping("/{id}")
    public Aula replace(@PathVariable("id") long id, @RequestBody Aula aula) {
        return this.aulaService.replace(id, aula);
    }
    @PostMapping("/{id}")
    public Aula createTarea(@PathVariable("id") long id, @RequestBody Tema tema) {
        return this.aulaService.createTema(id, tema);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        this.aulaService.delete(id);
    }
}
