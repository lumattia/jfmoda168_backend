package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.model.entity.Profesor;
import org.iesvdm.proyecto.model.entity.Tarea;
import org.iesvdm.proyecto.model.entity.Tema;
import org.iesvdm.proyecto.service.ProfesorService;
import org.iesvdm.proyecto.service.TemaService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/temas")
public class TemaController {
    private final TemaService temaService;
    private final ProfesorService profesorService;
    public TemaController(TemaService temaService, ProfesorService profesorService) {
        this.temaService = temaService;
        this.profesorService = profesorService;
    }
    private Profesor comprobarAccesoAula(long idAula) {
        // Realizar la comprobación de acceso al aula
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Profesor p = profesorService.one(auth.getName());
        if (p.getAulas().stream().noneMatch(aula -> aula.getId() == idAula)) {
            throw new AccessDeniedException("No eres profesor de ese aula.");
        }
        return p;
    }
    @GetMapping("/{id}")
    public Tema one(@PathVariable("id") long id) {
        Tema t=this.temaService.one(id);
        comprobarAccesoAula(t.getAula().getId());
        return t;
    }
    @PostMapping("/{id}")
    public Tarea createTarea(@PathVariable("id") long id, @RequestBody Tarea tarea) {
        Tema t=this.temaService.one(id);
        Profesor p=comprobarAccesoAula(t.getAula().getId());
        tarea.setPropietario(p);
        log.info("Guardando una aula");
        return this.temaService.createTarea(id, tarea);
    }
    @PutMapping("/{id}")
    public Tema replace(@PathVariable("id") long id, @RequestBody Tema tema) {
        Tema t=this.temaService.one(id);
        comprobarAccesoAula(t.getAula().getId());
        one(id);
        return this.temaService.replace(id, tema);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        Tema t=this.temaService.one(id);
        comprobarAccesoAula(t.getAula().getId());
        one(id);
        this.temaService.delete(id);
    }
}