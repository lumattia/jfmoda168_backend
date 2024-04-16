package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.model.entity.Asignatura;
import org.iesvdm.proyecto.service.AsignaturaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/asignaturas")
public class AsignaturaController {
    private final AsignaturaService asignaturaService;

    public AsignaturaController(AsignaturaService asignaturaService) {
        this.asignaturaService = asignaturaService;
    }
    @GetMapping({"","/"})
    public List<Asignatura> all(@RequestParam(value = "buscar",defaultValue = "") String buscar) {
        log.info("Accediendo a todas las asignaturas");
        return this.asignaturaService.allByFilter(buscar);
    }
    @PostMapping({"","/"})
    public Asignatura save(@RequestBody Asignatura asignatura) {
        log.info("Guardando una asignatura");
        return this.asignaturaService.save(asignatura);
    }
    @GetMapping("/{id}")
    public Asignatura one(@PathVariable("id") long id) {
        return this.asignaturaService.one(id);
    }
    @PutMapping("/{id}")
    public Asignatura replace(@PathVariable("id") long id, @RequestBody Asignatura asignatura) {
        return this.asignaturaService.replace(id, asignatura);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        this.asignaturaService.delete(id);
    }
}
