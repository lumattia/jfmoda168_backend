package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.domain.Estudiante;
import org.iesvdm.proyecto.service.EstudianteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping({"","/"})
    public List<Estudiante> all() {
        log.info("Accediendo a todas los estudiantes");
        return this.estudianteService.all();
    }
    @PostMapping({"","/"})
    public Estudiante save(@RequestBody Estudiante estudiante) {
        log.info("Guardando un estudiante");
        return this.estudianteService.save(estudiante);
    }
    @GetMapping("/{id}")
    public Estudiante one(@PathVariable("id") long id) {
        return this.estudianteService.one(id);
    }
    @PutMapping("/{id}")
    public Estudiante replace(@PathVariable("id") long id, @RequestBody Estudiante estudiante) {
        return this.estudianteService.replace(id, estudiante);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        this.estudianteService.delete(id);
    }
}
