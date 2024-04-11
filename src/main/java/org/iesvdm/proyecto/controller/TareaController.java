package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.model.entity.Tarea;
import org.iesvdm.proyecto.service.TareaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/tareas")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }
    @PostMapping({"","/"})
    public Tarea save(@RequestBody Tarea tarea) {
        log.info("Guardando una tarea");
        return this.tareaService.save(tarea);
    }
    @GetMapping("/{id}")
    public Tarea one(@PathVariable("id") long id) {
        return this.tareaService.one(id);
    }
    @PutMapping("/{id}")
    public Tarea replace(@PathVariable("id") long id, @RequestBody Tarea tarea) {
        return this.tareaService.replace(id, tarea);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        this.tareaService.delete(id);
    }
}