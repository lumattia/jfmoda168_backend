package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.model.entity.Estudiante;
import org.iesvdm.proyecto.model.view.EstudianteRow;
import org.iesvdm.proyecto.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/estudiantes")
public class EstudianteController {
    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private PasswordEncoder encoder;
    @GetMapping({"","/"})
    public Page<EstudianteRow> all(@RequestParam(value = "buscar",defaultValue = "") String buscar,
                                   Pageable pageable) {
        log.info("Accediendo a todas los estudiantes");
        return this.estudianteService.allByFilter(buscar,pageable);
    }
    @PostMapping({"","/"})
    public Estudiante save(@RequestBody Estudiante estudiante) {
        estudiante.setPassword(encoder.encode(estudiante.getPassword()));
        log.info("Guardando un estudiante");
        return this.estudianteService.save(estudiante);
    }
    @GetMapping("/{id}")
    public Estudiante one(@PathVariable("id") long id) {
        return this.estudianteService.one(id);
    }
    @PutMapping("/{id}")
    public Estudiante replace(@PathVariable("id") long id, @RequestBody Estudiante estudiante) {
        return this.estudianteService.replace(id, estudiante);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        this.estudianteService.delete(id);
    }
}
