package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.model.entity.Clase;
import org.iesvdm.proyecto.model.entity.Profesor;
import org.iesvdm.proyecto.model.entity.Tema;
import org.iesvdm.proyecto.model.view.Option;
import org.iesvdm.proyecto.model.view.ProfesorRow;
import org.iesvdm.proyecto.model.view.TareaRow;
import org.iesvdm.proyecto.service.ClaseService;
import org.iesvdm.proyecto.service.ProfesorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/clases")
public class ClaseController {
    private final ClaseService claseService;
    private final ProfesorService profesorService;

    public ClaseController(ClaseService claseService, ProfesorService profesorService) {
        this.claseService = claseService;
        this.profesorService = profesorService;
    }
    @GetMapping({"","/"})
    public Set<Option> all(@RequestParam(required = false, defaultValue = "-1") long curso,
                           @RequestParam(required = false, defaultValue = "-1") long asignatura) {
        log.info("Accediendo a todas las clases");
        return this.claseService.all(curso,asignatura);
    }
    @PostMapping({"","/"})
    public Clase save(@RequestBody Clase clase) {
        log.info("Guardando una clase");
        return this.claseService.save(clase);
    }
    @GetMapping("/{id}")
    public Clase one(@PathVariable("id") long id) {
        return this.claseService.one(id);
    }
    @GetMapping("/{id}/profesoresWithTarea")
    public Set<Profesor> getProfesores(@PathVariable("id") long id){
        return this.claseService.getProfesoresWithTarea(id);
    }
    @GetMapping("/{id}/profesores")
    public Set<ProfesorRow> getProfesores(@PathVariable("id") long id,
                                          @RequestParam(value = "buscar",defaultValue = "") String buscar){
        return this.claseService.getProfesores(id,buscar);
    }
    @GetMapping(value = "/{id}/aulas",params = "!buscar")
    public Set<Option> getAulas(@PathVariable("id") long id){
        return this.claseService.getAllAulas(id);
    }
    @GetMapping("/{id}/aulas")
    public Set<Option> getAulas(@PathVariable("id") long id,
                                          @RequestParam(value = "buscar",defaultValue = "") String buscar){
        return this.claseService.getAulas(id,buscar);
    }
    @GetMapping("/{id}/temas")
    public Set<Tema> getTemas(@PathVariable("id") long id,
                               @RequestParam(value = "aula") long idAula){
        if (one(id).getAulas().stream().anyMatch(aula -> aula.getId()==idAula))
            return this.claseService.getTemas(idAula);
        else{
            throw new NotFoundException("No se ha encontrado esa aula en esa clase.");
        }
    }
    @GetMapping("/{id}/tareas")
    public Page<TareaRow> getTareas(@PathVariable("id") long id,
                                    @RequestParam(value = "aula") long idAula,
                                    @RequestParam(value = "tema") long idTema,
                                    @RequestParam(value = "profesor") long idProfesor,
                                    Pageable pageable){
        return this.claseService.getTareas(id,idAula,idTema,idProfesor,pageable);
    }

    @PutMapping("/{id}")
    public Clase replace(@PathVariable("id") long id, @RequestBody Clase clase) {
        return this.claseService.replace(id, clase);
    }
    @PostMapping("/{id}/addProf")
    public Set<ProfesorRow> addProf(@PathVariable("id") long id, @RequestBody Set<Long> ids) {
        Set<Profesor> p=ids.stream().map(profesorService::one).collect(Collectors.toSet());
        return this.claseService.addProfs(id, p);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        this.claseService.delete(id);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}/profesor/{idProfesor}")
    public void removeProf(@PathVariable("id") long id,@PathVariable("idProfesor") long idProfesor){
        this.claseService.removeProf(id,idProfesor);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("aula/{idAula}")
    public void deleteAula(@PathVariable("idAula") long idAula){
        this.claseService.deleteAula(idAula);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("tema/{idTema}")
    public void deleteTema(@PathVariable("idTema") long idTema){
        this.claseService.deleteTema(idTema);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("tarea/{idTarea}")
    public void deleteTarea(@PathVariable("idTarea") long idTarea){
        this.claseService.deleteTarea(idTarea);
    }
}
