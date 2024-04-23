package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.model.entity.Aula;
import org.iesvdm.proyecto.model.entity.Profesor;
import org.iesvdm.proyecto.service.AulaService;
import org.iesvdm.proyecto.service.ProfesorService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/aulas")
public class AulaController {
    private final ProfesorService profesorService;
    private final AulaService aulaService;

    public AulaController(ProfesorService profesorService, AulaService aulaService) {
        this.profesorService = profesorService;
        this.aulaService = aulaService;
    }
    @PostMapping({"","/"})
    public Aula save(@RequestBody Aula aula) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Profesor p=profesorService.one(auth.getName());
        if (p!=null){
            aula.setPropietario(p);
            log.info("Guardando una aula");
            return this.aulaService.save(aula);
        }else{
            throw new RuntimeException("Solo los profesores pueden crear un aula.");
        }
    }
    @GetMapping("/{id}")
    public Aula one(@PathVariable("id") long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Profesor p=profesorService.one(auth.getName());
        if (p.getAulas().stream().anyMatch(aula -> aula.getId()==id)){
            return this.aulaService.one(id);
        }else{
            throw new RuntimeException("No eres profesor de ese aula.");
        }
    }
    @PutMapping("/{id}")
    public Aula replace(@PathVariable("id") long id, @RequestBody Aula aula) {
        return this.aulaService.replace(id, aula);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        this.aulaService.delete(id);
    }
}
