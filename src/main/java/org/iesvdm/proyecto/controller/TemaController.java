package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.mapper.MapStructMapper;
import org.iesvdm.proyecto.model.entity.Fase;
import org.iesvdm.proyecto.model.entity.Profesor;
import org.iesvdm.proyecto.model.entity.Tarea;
import org.iesvdm.proyecto.model.entity.Tema;
import org.iesvdm.proyecto.model.view.TareaDetail;
import org.iesvdm.proyecto.service.ProfesorService;
import org.iesvdm.proyecto.service.TareaService;
import org.iesvdm.proyecto.service.TemaService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(origins = "https://wuh.vercel.app")
@RestController
@RequestMapping("/v1/api/temas")
public class TemaController {
    private final MapStructMapper mapStructMapper;
    private final TemaService temaService;
    private final TareaService tareaService;
    private final ProfesorService profesorService;
    public TemaController(MapStructMapper mapStructMapper, TemaService temaService, TareaService tareaService, ProfesorService profesorService) {
        this.mapStructMapper = mapStructMapper;
        this.temaService = temaService;
        this.tareaService = tareaService;
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
    public TareaDetail createTarea(@PathVariable("id") long id, @RequestBody Tarea tarea) {
        Tema t=this.temaService.one(id);
        Profesor p=comprobarAccesoAula(t.getAula().getId());
        tarea.setPropietario(p);
        Fase basico=new Fase(1);
        Fase intermedio=new Fase(2);
        Fase avanzado=new Fase(3);
        tarea.getFases().add(basico);
        tarea.getFases().add(intermedio);
        tarea.getFases().add(avanzado);
        log.info("Guardando una tarea");
        return mapStructMapper.tareaToTareaDetail(this.temaService.createTarea(t, tarea));
    }
    @PostMapping("/{id}/addTareas")
    public Set<TareaDetail> addTareas(@PathVariable("id") long id, @RequestBody Set<Long> ids) {
        Tema t=this.temaService.one(id);
        Profesor p=comprobarAccesoAula(t.getAula().getId());

        long idClase=t.getAula().getClase().getId();
        List<Tarea> tareas=ids.stream()
                .map(tareaService::one)
                .filter(tarea -> tarea.getTema().getAula().getClase().getId()==idClase)
                .peek(tarea -> tarea.setPropietario(p))
                .toList();
        log.info("Guardando una aula");
        return this.temaService.addTareas(t, tareas)
                .stream()
                .filter(tarea -> !tarea.isEliminado())
                .map(mapStructMapper::tareaToTareaDetail)
                .collect(Collectors.toSet());
    }
    @PutMapping("/{id}")
    public Tema replace(@PathVariable("id") long id, @RequestBody Tema tema) {
        Tema t=one(id);
        return this.temaService.replace(t, tema);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        Tema t=one(id);
        this.temaService.delete(t);
    }
}