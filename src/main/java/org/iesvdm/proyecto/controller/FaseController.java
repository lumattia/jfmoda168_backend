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

import java.util.List;

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
            return (tareaEstudianteService.one(new TareaEstudiante.TareaEstudianteId(tarea,e))).getFase();
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
    public double done(@PathVariable("id") long TareaId,@PathVariable("nivel") byte nivel,@RequestBody List<Long> respuestas) {
        byte nivelMax=get(TareaId);
        if (nivel>nivelMax){
            throw new AccessDeniedException("Demasiado difícil para tí");
        }else {
            Fase f=faseService.one(TareaId,nivel);
            double result=faseService.entregar(f.getPreguntas(),respuestas);

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Estudiante e=estudianteService.one(auth.getName());
            Tarea tarea=tareaService.one(TareaId);
            TareaEstudiante t=tareaEstudianteService.one(new TareaEstudiante.TareaEstudianteId(tarea,e));
            tareaEstudianteService.saveResult(t,nivel,result);
            return result;
        }
    }
}
