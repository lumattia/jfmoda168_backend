package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.model.entity.Estudiante;
import org.iesvdm.proyecto.model.entity.Fase;
import org.iesvdm.proyecto.model.entity.Tarea;
import org.iesvdm.proyecto.model.entity.TareaEstudiante;
import org.iesvdm.proyecto.security.TokenUtils;
import org.iesvdm.proyecto.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/tarea/")
public class FaseController {
    @Autowired
    FaseService faseService;
    @Autowired
    TareaEstudianteService tareaEstudianteService;
    @Autowired
    EstudianteService estudianteService;
    @Autowired
    TareaService tareaService;
    @Autowired
    TokenUtils tokenUtils;
    @GetMapping("/{id}-{nivel}")
    public Fase one(@PathVariable("id") long id, @PathVariable("nivel") byte nivel, @RequestHeader("Authorization") String authorizationHeader) {
        Fase fase=this.faseService.one(id,nivel);

        //obtener el nivel mas dificil que el estudiante puede hacer
        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7); // Ignorar "Bearer "
        }
        Estudiante estudiante = estudianteService.one(tokenUtils.getTimeCreationUsername(token)[1].toString());
        Tarea tarea=tareaService.one(id);
        byte nivelMax=tareaEstudianteService.one(new TareaEstudiante.TareaEstudianteId(tarea,estudiante)).getFase();

        if (nivel>nivelMax){
            throw new RuntimeException("Demasiado difícil para tí");
        }
        return fase;
    }
    @PostMapping("/{id}-{nivel}")
    public Fase done(@PathVariable("id") long id,@PathVariable("id") byte nivel,@RequestBody Fase fase) {
        Tarea tarea=tareaService.one(id);
        if (nivel<1||nivel>3)
            nivel=1;
        return this.faseService.one(id,nivel);
    }
}
