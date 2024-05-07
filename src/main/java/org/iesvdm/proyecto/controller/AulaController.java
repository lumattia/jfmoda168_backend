package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.model.entity.*;
import org.iesvdm.proyecto.model.view.EstudianteRow;
import org.iesvdm.proyecto.model.view.ProfesorRow;
import org.iesvdm.proyecto.service.AulaService;
import org.iesvdm.proyecto.service.EstudianteService;
import org.iesvdm.proyecto.service.ProfesorService;
import org.iesvdm.proyecto.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.AccessDeniedException;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/aulas")
public class AulaController {
    private final ProfesorService profesorService;
    private final EstudianteService estudianteService;
    private final UsuarioService usuarioService;
    private final AulaService aulaService;

    public AulaController(ProfesorService profesorService, EstudianteService estudianteService, UsuarioService usuarioService, AulaService aulaService) {
        this.profesorService = profesorService;
        this.estudianteService = estudianteService;
        this.usuarioService = usuarioService;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario p=usuarioService.one(auth.getName());
        Aula a=get(id);
        boolean found;
        found=a.getProfesores().stream()
                .map(Usuario::getId)
                .anyMatch(i-> Objects.equals(i, p.getId()));
        if (!found)
            found=a.getEstudiantes().stream()
                    .map(Usuario::getId)
                    .anyMatch(i-> Objects.equals(i, p.getId()));
        if (!found){
            throw new AccessDeniedException("No eres usuario de esa aula");
        }else{
            return this.aulaService.getProfesores(id,buscar);
        }
    }
    @GetMapping("/{id}/estudiantes")
    public Set<EstudianteRow> getEstudiantes(@PathVariable("id") long id,
                                             @RequestParam(value = "buscar",defaultValue = "") String buscar){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario p=usuarioService.one(auth.getName());
        Aula a=get(id);
        boolean found;
        found=a.getProfesores().stream()
                .map(Usuario::getId)
                .anyMatch(i-> Objects.equals(i, p.getId()));
        if (!found)
            found=a.getEstudiantes().stream()
                    .map(Usuario::getId)
                    .anyMatch(i-> Objects.equals(i, p.getId()));
        if (!found){
            throw new AccessDeniedException("No eres usuario de esa aula");
        }else{
            return this.aulaService.getEstudiantes(id,buscar);
        }
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
    @PostMapping("/{id}/addProf")
    public Set<ProfesorRow> addProf(@PathVariable("id") long id, @RequestBody Set<Long> ids) {
        comprobarAccesoAula(id);
        Set<Profesor> p=ids.stream().map(profesorService::one).collect(Collectors.toSet());
        return this.aulaService.addProf(id, p);
    }
    @PostMapping("/{id}/addEst")
    public Set<EstudianteRow> addEst(@PathVariable("id") long id, @RequestBody Set<Long> ids) {
        comprobarAccesoAula(id);
        Set<Estudiante> p=ids.stream().map(estudianteService::one).collect(Collectors.toSet());
        return this.aulaService.addEst(id, p);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        comprobarAccesoAula(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Profesor p = profesorService.one(auth.getName());
        this.aulaService.delete(id,p);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}/profesor/{idProfesor}")
    public void removeProf(@PathVariable("id") long id,@PathVariable("idProfesor") long idProfesor){
        comprobarAccesoAula(id);
        this.aulaService.removeProf(id,idProfesor);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}/estudiante/{idEstudiante}")
    public void removeEst(@PathVariable("id") long id,@PathVariable("idEstudiante") long idEstudiante){
        comprobarAccesoAula(id);
        this.aulaService.removeEst(id,idEstudiante);
    }
    private Aula get(long id) {
        return this.aulaService.one(id);
    }
}
