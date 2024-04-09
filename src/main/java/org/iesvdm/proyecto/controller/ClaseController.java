package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.model.Clase;
import org.iesvdm.proyecto.model.Profesor;
import org.iesvdm.proyecto.service.ClaseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/clases")
public class ClaseController {
    private final ClaseService claseService;

    public ClaseController(ClaseService claseService) {
        this.claseService = claseService;
    }

    @GetMapping({"","/"})
    public Set<Clase> all(@RequestParam(required = false, defaultValue = "-1") long curso,
                          @RequestParam(required = false, defaultValue = "-1") long asignatura) {
        log.info("Accediendo a todas las clases");
        return this.claseService.all(curso,asignatura);
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
    public Clase replace(@PathVariable("id") long id, @RequestBody Clase clase) {
        return this.claseService.replace(id, clase);
    }
    @PostMapping("/{id}")
    public Clase addProf(@PathVariable("id") long id, @RequestBody Set<Profesor> profesor) {
        return this.claseService.add(id, profesor);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        this.claseService.delete(id);
    }
}
