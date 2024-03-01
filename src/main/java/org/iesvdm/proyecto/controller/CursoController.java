package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.domain.Curso;
import org.iesvdm.proyecto.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cursos")
public class CursoController {
    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping({"","/"})
    public List<Curso> all() {
        log.info("Accediendo a todas los cursos");
        return this.cursoService.all();
    }
    @PostMapping({"","/"})
    public Curso save(@RequestBody Curso curso) {
        log.info("Guardando un curso");
        return this.cursoService.save(curso);
    }
    @GetMapping("/{id}")
    public Curso one(@PathVariable("id") String id) {
        return this.cursoService.one(id);
    }
    @PutMapping("/{id}")
    public Curso replace(@PathVariable("id") String id, @RequestBody Curso curso) {
        return this.cursoService.replace(id, curso);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        this.cursoService.delete(id);
    }
}