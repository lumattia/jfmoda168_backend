package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.mapper.MapStructMapper;
import org.iesvdm.proyecto.model.entity.*;
import org.iesvdm.proyecto.model.view.EstudianteRow;
import org.iesvdm.proyecto.model.view.Option;
import org.iesvdm.proyecto.model.view.PuntoTarea;
import org.iesvdm.proyecto.service.EstudianteService;
import org.iesvdm.proyecto.service.TareaEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/estudiantes")
public class EstudianteController {
    @Autowired
    private EstudianteService estudianteService;
    @Autowired
    private TareaEstudianteService tareaEstudianteService;
    @Autowired
    private MapStructMapper mapStructMapper;

    @Autowired
    private PasswordEncoder encoder;
    @GetMapping({"notBlocked"})
    public Page<EstudianteRow> allNotBlocked(@RequestParam(value = "buscar",defaultValue = "") String buscar,
                                           Pageable pageable) {
        log.info("Accediendo a todos los profesores");
        return this.estudianteService.allByFilterNotBlocked(buscar,pageable);
    }
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
    @GetMapping("/getAulas")
    public Set<Option> getAulas() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Estudiante e=estudianteService.one(auth.getName());
        return this.estudianteService.getAulas(e.getId());
    }
    @GetMapping("/getPuntos/{idAula}")
    public Map<String,List<PuntoTarea>> getPuntos(@PathVariable("idAula") long idAula) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Estudiante e=estudianteService.one(auth.getName());
        if (e.getAulas().stream().noneMatch(aula -> aula.getId() == idAula)) {
            throw new AccessDeniedException("No eres estudiante de esa aula.");
        }
        Aula a=this.estudianteService.getAula(idAula);
        return getStringListMap(a, e);
    }

    private Map<String, List<PuntoTarea>> getStringListMap(Aula a, Estudiante e) {
        return  a.getTemas().stream()
                .flatMap(tema -> tema.getTareas().stream()
                        .map(t -> mapStructMapper.tareaEstudianteToPuntoTarea(tareaEstudianteService.one(new TareaEstudiante.TareaEstudianteId(t,e))))
                        .map(puntoTarea -> new AbstractMap.SimpleEntry<>(tema.getNombre(), puntoTarea))
                ).collect(Collectors.groupingBy(
                        AbstractMap.SimpleEntry::getKey,
                        Collectors.mapping(AbstractMap.SimpleEntry::getValue, Collectors.toList())
                ));
    }

    @GetMapping("/getAulas/{idAula}/nombre")
    public Map<String, String> nombreAula(@PathVariable("idAula") long idAula) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Estudiante e=estudianteService.one(auth.getName());
        if (e.getAulas().stream().noneMatch(aula -> aula.getId() == idAula)) {
            throw new AccessDeniedException("No eres estudiante de esa aula.");
        }
        Aula a=this.estudianteService.getAula(idAula);
        String nombreAula = a.getClase().getNombre() + " " + a.getGrupo() + " " + a.getAnio();

        Map<String, String> response = new HashMap<>();
        response.put("nombreAula", nombreAula);
        return response;
    }
    @GetMapping("/getAulas/{idAula}/temas")
    public List<Tema> getTemas(@PathVariable("idAula") long idAula) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Estudiante e=estudianteService.one(auth.getName());
        if (e.getAulas().stream().noneMatch(aula -> aula.getId() == idAula)) {
            throw new AccessDeniedException("No eres estudiante de esa aula.");
        }
        return this.estudianteService.getAula(idAula).getTemas();
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
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/salirAula/{idAula}")
    public void salirAula(@PathVariable("idAula") long idAula){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Estudiante e = estudianteService.one(auth.getName());
        this.estudianteService.salirAula(e,idAula);
    }

}
