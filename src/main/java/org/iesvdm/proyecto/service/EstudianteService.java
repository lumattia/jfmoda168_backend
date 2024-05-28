package org.iesvdm.proyecto.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.iesvdm.proyecto.model.entity.Aula;
import org.iesvdm.proyecto.model.entity.Estudiante;
import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.model.entity.Tarea;
import org.iesvdm.proyecto.model.entity.Tema;
import org.iesvdm.proyecto.model.view.EstudianteRow;
import org.iesvdm.proyecto.model.view.Option;
import org.iesvdm.proyecto.repository.EstudianteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EstudianteService {
    @PersistenceContext
    EntityManager em;
    private final EstudianteRepository estudianteRepository;
    public EstudianteService(EstudianteRepository estudianteRepository ) {
        this.estudianteRepository = estudianteRepository;
    }
    public Page<EstudianteRow> allByFilterNotBlocked(String buscar, Pageable pageable) {
        return this.estudianteRepository.findSearchNotBlocked(buscar,pageable);
    }
    public Page<EstudianteRow> allByFilter(String buscar, Pageable pageable) {
        return this.estudianteRepository.findByNombreCompleto(buscar,pageable);
    }
    public Estudiante save(Estudiante estudiante) {
        return this.estudianteRepository.save(estudiante);
    }

    public Estudiante one(Long id) {
        return this.estudianteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id,"estudiante"));
    }
    public Estudiante one(String email) {
        return this.estudianteRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Estudiante with Email: "+email+" not found"));
    }
    public Aula getAula(Long id) {
        Aula a= this.estudianteRepository.getAula(id)
                .orElseThrow(() -> new NotFoundException(id,"aula"));
        if (a.isEliminado()){
            throw new NotFoundException("Aula eliminada");
        }
        a.getTemas().removeIf(Tema::isEliminado);
        a.getTemas().sort(Tema::compareTo);
        a.getTemas().forEach(tema -> {
            tema.getTareas().removeIf(tarea -> tarea.isEliminado()|| !tarea.getVisible());
            tema.getTareas().sort(Tarea::compareTo);
        });
        return a;
    }
    public Set<Option> getAulas(Long id) {
        return this.estudianteRepository.getAulas(id);
    }

    public Estudiante replace(Long id, Estudiante estudiante) {
        Estudiante e =this.estudianteRepository.findById(id).orElseThrow(() -> new NotFoundException(id,"estudiante"));
        e.setEmail(estudiante.getEmail());
        e.setNombre(estudiante.getNombre());
        e.setApellidos(estudiante.getApellidos());
        e.setAula(estudiante.getAula());
        estudianteRepository.save(e);
        return e;
    }

    public void delete(Long id) {
        this.estudianteRepository.findById(id).map(e -> {
                    this.estudianteRepository.delete(e);
                    return e;})
                .orElseThrow(() -> new NotFoundException(id,"estudiante"));
    }
    @Transactional
    public void salirAula(Estudiante e,Long idAula) {
        Aula a=em.find(Aula.class,idAula);
        if (a.getEstudiantes().remove(e)){
            em.merge(a);
        }else{
            throw new NotFoundException("No se ha encontrado ese estudiante en esa aula");
        }
    }
}
