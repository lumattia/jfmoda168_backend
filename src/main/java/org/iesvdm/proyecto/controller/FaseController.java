package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.model.entity.*;
import org.iesvdm.proyecto.security.TokenUtils;
import org.iesvdm.proyecto.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public Fase one(@PathVariable("id") long TareaId, @PathVariable("nivel") byte nivel) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String rol=auth.getAuthorities().stream().findAny().map(GrantedAuthority::getAuthority).orElse("");
        if (rol.equals("ESTUDIANTE")){
            Estudiante e=estudianteService.one(auth.getName());
            Tarea tarea=tareaService.one(TareaId);
            byte nivelMax=tareaEstudianteService.one(new TareaEstudiante.TareaEstudianteId(tarea,e)).getFase();
            if (nivel>nivelMax){
                throw new RuntimeException("Demasiado difícil para tí");
            }else{
                return faseService.one(TareaId,nivel);
            }
        }else{
            throw new RuntimeException("No autorizado");
        }

    }
    @PostMapping("/{id}-{nivel}")
    public Fase done(@PathVariable("id") long id,@PathVariable("id") byte nivel,@RequestBody Fase fase) {
        Tarea tarea=tareaService.one(id);
        if (nivel<1||nivel>3)
            nivel=1;
        return this.faseService.one(id,nivel);
    }
}
