package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.mapper.MapStructMapper;
import org.iesvdm.proyecto.model.entity.Estudiante;
import org.iesvdm.proyecto.model.entity.Profesor;
import org.iesvdm.proyecto.model.entity.Tarea;
import org.iesvdm.proyecto.model.entity.TareaEstudiante;
import org.iesvdm.proyecto.model.view.PuntoEstudiante;
import org.iesvdm.proyecto.model.view.TareaDetail;
import org.iesvdm.proyecto.model.view.TareaFase;
import org.iesvdm.proyecto.service.EstudianteService;
import org.iesvdm.proyecto.service.ProfesorService;
import org.iesvdm.proyecto.service.TareaEstudianteService;
import org.iesvdm.proyecto.service.TareaService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/tareas")
public class TareaController {
    private final MapStructMapper mapStructMapper;
    private final TareaService tareaService;
    private final TareaEstudianteService tareaEstudianteService;
    private final ProfesorService profesorService;
    private final EstudianteService estudianteService;
    public TareaController(MapStructMapper mapStructMapper, TareaService tareaService, TareaEstudianteService tareaEstudianteService, ProfesorService profesorService, EstudianteService estudianteService) {
        this.mapStructMapper = mapStructMapper;
        this.tareaService = tareaService;
        this.tareaEstudianteService = tareaEstudianteService;
        this.profesorService = profesorService;
        this.estudianteService = estudianteService;
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

    @GetMapping("/{id}")
    public TareaFase one(@PathVariable("id") long id) {
        return mapStructMapper.tareaToTareaFase(this.tareaService.one(id));
    }
    @GetMapping("/{id}/puntos")
    public List<PuntoEstudiante> getPuntos(@PathVariable("id") long id) {
        Tarea t=get(id);
        comprobarAccesoAula(t.getTema().getAula().getId());
        Set<Estudiante> estudiantes=t.getTema().getAula().getEstudiantes();
        List<PuntoEstudiante> result=new ArrayList<>();
        estudiantes.forEach(e->result.add(mapStructMapper.tareaEstudianteToPuntoEstudiante(tareaEstudianteService.one(new TareaEstudiante.TareaEstudianteId(t,e)))));
        return result;
    }
    @PutMapping("/{id}/cambiarNombre")
    public TareaDetail cambiarNombre(@PathVariable("id") long id, @RequestBody Tarea tarea) {
        Tarea t=get(id);
        comprobarAccesoAula(t.getTema().getAula().getId());
        return mapStructMapper.tareaToTareaDetail(this.tareaService.cambiarNombre(t, tarea));
    }
    @PutMapping("/{id}/estudiante/{estudianteId}/{nivel}")
    public byte cambiarNivel(@PathVariable("id") long id,
                             @PathVariable("estudianteId") long estudianteId,
                             @PathVariable("nivel") byte nivel) {
        Tarea t=get(id);
        comprobarAccesoAula(t.getTema().getAula().getId());
        Estudiante e=estudianteService.one(estudianteId);
        return tareaEstudianteService.replace(new TareaEstudiante.TareaEstudianteId(t,e),nivel);
    }
    @PutMapping("/{id}")
    public TareaFase replace(@PathVariable("id") long id, @RequestBody Tarea tarea) {
        Tarea t=get(id);
        comprobarAccesoAula(t.getTema().getAula().getId());
        return mapStructMapper.tareaToTareaFase(this.tareaService.replace(t, tarea));
    }
    @PutMapping("/{id}/cambiarEstado")
    public boolean cambiarEstado(@PathVariable("id") long id) {
        Tarea t=get(id);
        comprobarAccesoAula(t.getTema().getAula().getId());
        return this.tareaService.cambiarEstado(t);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        Tarea t=get(id);
        comprobarAccesoAula(t.getTema().getAula().getId());
        this.tareaService.delete(t);
    }
    private Tarea get(long id) {
        return this.tareaService.one(id);
    }
}