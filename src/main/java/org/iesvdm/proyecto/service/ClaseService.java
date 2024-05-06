package org.iesvdm.proyecto.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.iesvdm.proyecto.model.entity.*;
import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.model.view.Option;
import org.iesvdm.proyecto.model.view.ProfesorRow;
import org.iesvdm.proyecto.model.view.TareaRow;
import org.iesvdm.proyecto.repository.ClaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClaseService {
    @PersistenceContext
    EntityManager em;
    private final ClaseRepository claseRepository;
    public ClaseService(ClaseRepository claseRepository) {
        this.claseRepository = claseRepository;
    }

    public Set<Option> all(Long curso, Long asignatura) {
            return this.claseRepository.getButtonsFiltering(curso, asignatura);
    }
    public Set<ProfesorRow> getProfesores(long id,String buscar) {
        return this.claseRepository.getProfesores(id,buscar);
    }
    public Set<Profesor> getProfesoresWithTarea(long id) {
        return this.claseRepository.getProfesoresWithTarea(id);
    }
    public Set<Option> getAulas(long id,String buscar) {
        return this.claseRepository.getAulas(id,buscar);
    }
    public Set<Tema> getTemas(Long idAula) {
        return this.claseRepository.getTemas(idAula);
    }
    public Page<TareaRow> getTareas(Long claseId, Long idAula, Long idTema, Long idProfesor, Pageable pageable) {
        return this.claseRepository.getTareas(claseId,idAula, idTema, idProfesor,pageable);
    }
    public Clase one(Long id) {
        return this.claseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id,"clase"));
    }
    public Clase replace(Long id, Clase clase) {
        return this.claseRepository.findById(id).map( c -> (id.equals(clase.getId())  ?
                        this.claseRepository.save(clase) : null))
                .orElseThrow(() -> new NotFoundException(id,"clase"));
    }
    @Transactional
    public Clase save(Clase clase) {
        this.claseRepository.save(clase);
        this.em.refresh(clase);
        return clase;
    }
    public Set<ProfesorRow> addProfs(Long id, Set<Profesor> profesor) {
        this.claseRepository.findById(id).map( c -> {
                    c.getProfesores().addAll(profesor);
                    return claseRepository.save(c);
                }).orElseThrow(() -> new NotFoundException(id,"clase"));
        return profesor.stream().map(Profesor::toProfesorRow).collect(Collectors.toSet());
    }
    public void delete(Long id) {
        this.claseRepository.findById(id).map(c -> {
                    this.claseRepository.delete(c);
                    return c;})
                .orElseThrow(() -> new NotFoundException(id,"clase"));
    }
    public void removeProf(long id,long idProf) {
        Clase c=this.claseRepository.findById(id).orElseThrow(() -> new NotFoundException(id,"clase"));
        Profesor p=new Profesor();
        p.setId(idProf);
        if (c.getProfesores().remove(p)){
            this.claseRepository.save(c);
        }else{
            throw new NotFoundException("No se ha encontrado ese profesor en esa clase");
        }
    }
    @Transactional
    public void deleteAula(long idAula) {
        Aula a = em.find(Aula.class, idAula);
        em.remove(a);
    }
    @Transactional
    public void deleteTema(long idTema) {
        Tema tema = em.find(Tema.class, idTema);
        em.remove(tema);
    }
    @Transactional
    public void deleteTarea(long idTarea) {
        Tarea tarea = em.find(Tarea.class, idTarea);
        em.remove(tarea);
    }
}
