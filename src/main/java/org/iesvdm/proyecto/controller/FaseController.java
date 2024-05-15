package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.mapper.MapStructMapper;
import org.iesvdm.proyecto.model.entity.*;
import org.iesvdm.proyecto.model.view.FaseEstudiante;
import org.iesvdm.proyecto.security.TokenUtils;
import org.iesvdm.proyecto.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.AccessDeniedException;
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/fases/")
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
    @Autowired
    MapStructMapper mapStructMapper;

    @GetMapping("/{id}")
    public byte get(@PathVariable("id") long TareaId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String rol=auth.getAuthorities().stream().findAny().map(GrantedAuthority::getAuthority).orElse("");
        if (rol.equals("ESTUDIANTE")){
            Estudiante e=estudianteService.one(auth.getName());
            Tarea tarea=tareaService.one(TareaId);
            return tareaEstudianteService.one(new TareaEstudiante.TareaEstudianteId(tarea,e)).getFase();
        }else{
            throw new AccessDeniedException("No autorizado");
        }
    }

    @GetMapping("/{id}/{nivel}")
    public FaseEstudiante one(@PathVariable("id") long TareaId, @PathVariable("nivel") byte nivel) {
        byte nivelMax=get(TareaId);
        if (nivel>nivelMax){
            throw new AccessDeniedException("Demasiado difícil para tí");
        }else{
            return mapStructMapper.faseToFaseEstudiante(faseService.one(TareaId,nivel));
        }
    }

    @PostMapping("/{id}/{nivel}")
    public FaseEstudiante done(@PathVariable("id") long id,@PathVariable("id") byte nivel,@RequestBody Fase fase) {
        Tarea tarea=tareaService.one(id);
        if (nivel<1||nivel>3)
            nivel=1;
        return mapStructMapper.faseToFaseEstudiante(faseService.one(id,nivel));
    }
}
