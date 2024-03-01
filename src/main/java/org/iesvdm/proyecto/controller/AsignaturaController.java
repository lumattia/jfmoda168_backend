package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.domain.Asignatura;
import org.iesvdm.proyecto.service.AsignaturaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/asignaturas")
public class AsignaturaController {
    private final AsignaturaService asignaturaService;

    public AsignaturaController(AsignaturaService asignaturaService) {
        this.asignaturaService = asignaturaService;
    }

    @GetMapping({"","/"})
    public List<Asignatura> all() {
        log.info("Accediendo a todas las asignaturas");
        return this.asignaturaService.all();
    }
    @PostMapping({"","/"})
    public Asignatura save(@RequestBody Asignatura asignatura) {
        log.info("Guardando una asignatura");
        return this.asignaturaService.save(asignatura);
    }
    @GetMapping("/{id}")
    public Asignatura one(@PathVariable("id") String id) {
        return this.asignaturaService.one(id);
    }
    @PutMapping("/{id}")
    public Asignatura replace(@PathVariable("id") String id, @RequestBody Asignatura pelicula) {
        return this.asignaturaService.replace(id, pelicula);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        this.asignaturaService.delete(id);
    }
}
