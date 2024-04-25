package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.model.entity.Aula;
import org.iesvdm.proyecto.model.entity.Profesor;
import org.iesvdm.proyecto.model.entity.Tema;
import org.iesvdm.proyecto.model.view.EstudianteRow;
import org.iesvdm.proyecto.model.view.ProfesorRow;
import org.iesvdm.proyecto.service.AulaService;
import org.iesvdm.proyecto.service.ProfesorService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.AccessDeniedException;

import java.util.Set;

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
    private Profesor comprobarAccesoAula(long idAula) {
        // Realizar la comprobación de acceso al aula
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Profesor p = profesorService.one(auth.getName());
        if (p.getAulas().stream().noneMatch(aula -> aula.getId() == idAula)) {
            throw new AccessDeniedException("No eres profesor de esa aula.");
        }
        return p;
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
            throw new AccessDeniedException("Solo los profesores pueden crear un aula.");
        }
    }
    @GetMapping("/{id}")
    public Aula one(@PathVariable("id") long id) {
        comprobarAccesoAula(id);
        return this.aulaService.one(id);
    }
    @GetMapping("/{id}/profesores")
    public Set<ProfesorRow> getProfesores(@PathVariable("id") long id,
                                          @RequestParam(value = "buscar",defaultValue = "") String buscar){
        comprobarAccesoAula(id);
        return this.aulaService.getProfesores(id,buscar);
    }
    @GetMapping("/{id}/estudiantes")
    public Set<EstudianteRow> getEstudiantes(@PathVariable("id") long id,
                                             @RequestParam(value = "buscar",defaultValue = "") String buscar){
        comprobarAccesoAula(id);
        return this.aulaService.getEstudiantes(id,buscar);
    }
    @PutMapping("/{id}")
    public Aula replace(@PathVariable("id") long id, @RequestBody Aula aula) {
        comprobarAccesoAula(id);
        return this.aulaService.replace(id, aula);
    }
    @PostMapping("/{id}")
    public Tema createTema(@PathVariable("id") long id, @RequestBody Tema tema) {
        comprobarAccesoAula(id);
        return this.aulaService.createTema(id, tema);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Profesor p = profesorService.one(auth.getName());
        this.aulaService.delete(id,p.getId());
    }
}
