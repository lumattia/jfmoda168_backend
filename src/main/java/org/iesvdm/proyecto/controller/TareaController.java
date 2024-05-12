package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.mapper.MapStructMapper;
import org.iesvdm.proyecto.model.entity.Profesor;
import org.iesvdm.proyecto.model.entity.Tarea;
import org.iesvdm.proyecto.model.view.TareaDetail;
import org.iesvdm.proyecto.model.view.TareaFase;
import org.iesvdm.proyecto.service.ProfesorService;
import org.iesvdm.proyecto.service.TareaService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/tareas")
public class TareaController {
    private final MapStructMapper mapStructMapper;
    private final TareaService tareaService;
    private final ProfesorService profesorService;
    public TareaController(MapStructMapper mapStructMapper, TareaService tareaService, ProfesorService profesorService) {
        this.mapStructMapper = mapStructMapper;
        this.tareaService = tareaService;
        this.profesorService = profesorService;
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
    @PutMapping("/{id}/cambiarNombre")
    public TareaDetail cambiarNombre(@PathVariable("id") long id, @RequestBody Tarea tarea) {
        Tarea t=get(id);
        comprobarAccesoAula(t.getTema().getAula().getId());
        return mapStructMapper.tareaToTareaDetail(this.tareaService.cambiarNombre(t, tarea));
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