package org.iesvdm.proyecto.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.iesvdm.proyecto.model.entity.Aula;
import org.iesvdm.proyecto.model.entity.Clase;
import org.iesvdm.proyecto.model.entity.Profesor;
import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.model.view.Option;
import org.iesvdm.proyecto.model.view.ProfesorRow;
import org.iesvdm.proyecto.repository.ClaseRepository;
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
    public Set<ProfesorRow> getProfesoresWithTarea(long id) {
        return this.claseRepository.getProfesoresWithTareaInClass(id);
    }
    public Set<Option> getAllAulas(long id) {
        return this.claseRepository.getAllAulas(id);
    }
    public Set<Option> getAulas(long id,String buscar) {
        return this.claseRepository.getAulas(id,buscar);
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
        return profesor.stream().map(p -> new ProfesorRow() {
            @Override
            public long getId() {
                return p.getId();
            }
            @Override
            public String getNombre() {
                return p.getNombre();
            }

            @Override
            public String getApellidos() {
                return p.getApellidos();
            }

            @Override
            public String getEmail() {
                return p.getEmail();
            }

            @Override
            public boolean isBlocked() {
                return p.isBlocked();
            }
        }).collect(Collectors.toSet());
    }
    public void delete(Long id) {
        this.claseRepository.findById(id).map(c -> {
                    this.claseRepository.delete(c);
                    return c;})
                .orElseThrow(() -> new NotFoundException(id,"clase"));
    }
    public void removeProf(long id,long idProf) {
        Clase c=this.claseRepository.findById(id).orElseThrow(() -> new NotFoundException(id,"aula"));
        Profesor p=new Profesor();
        p.setId(idProf);
        if (c.getProfesores().remove(p)){
            this.claseRepository.save(c);
        }else{
            throw new NotFoundException("No se ha encontrado ese profesor en esa clase");
        }
    }
    public void deleteAula(long id,long idAula) {
        Clase c=this.claseRepository.findById(id).orElseThrow(() -> new NotFoundException(id,"aula"));
        Aula a=new Aula();
        a.setId(idAula);
        if (c.getAulas().remove(a)){
            this.claseRepository.save(c);
        }else{
            throw new NotFoundException("No se ha encontrado esa aula en esa clase");
        }
    }
}
