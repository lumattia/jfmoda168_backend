package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.domain.Clase;
import org.iesvdm.proyecto.service.ClaseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/api/clases")
public class ClaseController {
    private final ClaseService claseService;

    public ClaseController(ClaseService claseService) {
        this.claseService = claseService;
    }

    @GetMapping({"","/"})
    public List<Clase> all() {
        log.info("Accediendo a todas las clases");
        return this.claseService.all();
    }
    @PostMapping({"","/"})
    public Clase save(@RequestBody Clase clase) {
        log.info("Guardando una clase");
        return this.claseService.save(clase);
    }
    @GetMapping("/{id}")
    public Clase one(@PathVariable("id") long id) {
        return this.claseService.one(id);
    }
    @PutMapping("/{id}")
    public Clase replace(@PathVariable("id") long id, @RequestBody Clase pelicula) {
        return this.claseService.replace(id, pelicula);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        this.claseService.delete(id);
    }
}
