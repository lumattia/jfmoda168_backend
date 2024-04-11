package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.model.entity.Curso;
import org.iesvdm.proyecto.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/cursos")
public class CursoController {
    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping({"","/"})
    public List<Curso> all(@RequestParam(value = "buscar",defaultValue = "") String buscar) {
        log.info("Accediendo a todas los cursos");
        return this.cursoService.allByFilter(buscar);
    }
    @PostMapping({"","/"})
    public Curso save(@RequestBody Curso curso) {
        log.info("Guardando un curso");
        return this.cursoService.save(curso);
    }
    @GetMapping("/{id}")
    public Curso one(@PathVariable("id") long id) {
        return this.cursoService.one(id);
    }
    @PutMapping("/{id}")
    public Curso replace(@PathVariable("id") long id, @RequestBody Curso curso) {
        return this.cursoService.replace(id, curso);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        this.cursoService.delete(id);
    }
}