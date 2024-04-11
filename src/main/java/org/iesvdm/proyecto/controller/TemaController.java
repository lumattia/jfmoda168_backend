package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.model.entity.Tema;
import org.iesvdm.proyecto.service.TemaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/temas")
public class TemaController {

    private final TemaService temaService;

    public TemaController(TemaService temaService) {
        this.temaService = temaService;
    }
    @PostMapping({"","/"})
    public Tema save(@RequestBody Tema tema) {
        log.info("Guardando un tema");
        return this.temaService.save(tema);
    }
    @GetMapping("/{id}")
    public Tema one(@PathVariable("id") long id) {
        return this.temaService.one(id);
    }
    @PutMapping("/{id}")
    public Tema replace(@PathVariable("id") long id, @RequestBody Tema tema) {
        return this.temaService.replace(id, tema);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        this.temaService.delete(id);
    }
}