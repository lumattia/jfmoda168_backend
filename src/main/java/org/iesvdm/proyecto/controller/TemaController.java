package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.model.entity.Profesor;
import org.iesvdm.proyecto.model.entity.Tema;
import org.iesvdm.proyecto.service.ProfesorService;
import org.iesvdm.proyecto.service.TemaService;
import org.springframework.http.HttpStatus;
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
    private void comprobarAccesoAula(long idAula) {
        // Realizar la comprobación de acceso al aula
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Profesor p = profesorService.one(auth.getName());
        if (p.getAulas().stream().noneMatch(aula -> aula.getId() == idAula)) {
            throw new RuntimeException("No eres profesor de ese aula.");
        }
    }
    @PostMapping({"","/"})
    public Tema save(@RequestBody Tema tema) {
        comprobarAccesoAula(tema.getAula().getId());
        log.info("Guardando un tema");
        return this.temaService.save(tema);
    }
    @GetMapping("/{id}")
    public Tema one(@PathVariable("id") long id) {
        Tema t=this.temaService.one(id);
        comprobarAccesoAula(t.getAula().getId());
        return t;
    }
    @PutMapping("/{id}")
    public Tema replace(@PathVariable("id") long id, @RequestBody Tema tema) {
        one(id);
        return this.temaService.replace(id, tema);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        one(id);
        this.temaService.delete(id);
    }
}