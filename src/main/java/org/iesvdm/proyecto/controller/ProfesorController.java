package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.model.entity.Profesor;
import org.iesvdm.proyecto.model.view.Option;
import org.iesvdm.proyecto.model.view.ProfesorRow;
import org.iesvdm.proyecto.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/profesores")
public class ProfesorController {
    @Autowired
    ProfesorService profesorService;
    @Autowired
    PasswordEncoder encoder;
    @GetMapping({"notBlocked"})
    public Page<ProfesorRow> allNotBlocked(@RequestParam(value = "buscar",defaultValue = "") String buscar,
                                      Pageable pageable) {
        log.info("Accediendo a todos los profesores");
        return this.profesorService.allByFilterNotBlocked(buscar,pageable);
    }
    @GetMapping({"","/"})
    public Page<ProfesorRow> allPaged(@RequestParam(value = "buscar",defaultValue = "") String buscar,
                                 Pageable pageable) {
        log.info("Accediendo a todos los profesores");
        return this.profesorService.allByFilter(buscar,pageable);
    }
    @PostMapping({"","/"})
    public Profesor save(@RequestBody Profesor profesor) {
        profesor.setPassword(encoder.encode(profesor.getPassword()));
        log.info("Guardando un profesor");
        return this.profesorService.save(profesor);
    }
    @GetMapping("/{id}")
    public Profesor one(@PathVariable("id") long id) {
        return this.profesorService.one(id);
    }
    @GetMapping("/getAulas")
    public Set<Option> getAulas() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Profesor p=profesorService.one(auth.getName());
        return this.profesorService.getAulas(p.getId());
    }
    @GetMapping("/getClases")
    public Set<Option> getClases() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Profesor p=profesorService.one(auth.getName());
        return this.profesorService.getClases(p.getId());
    }
    @PutMapping("/{id}")
    public Profesor replace(@PathVariable("id") long id, @RequestBody Profesor profesor) {
        return this.profesorService.replace(id, profesor);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        this.profesorService.delete(id);
    }
}
