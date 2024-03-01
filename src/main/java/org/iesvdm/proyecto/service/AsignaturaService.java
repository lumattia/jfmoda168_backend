package org.iesvdm.proyecto.service;

import org.iesvdm.proyecto.domain.Asignatura;
import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.repository.AsignaturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturaService {
    private final AsignaturaRepository asignaturaRepository;
    public AsignaturaService(AsignaturaRepository asignaturaRepository) {
        this.asignaturaRepository = asignaturaRepository;
    }

    public List<Asignatura> all() {
        return this.asignaturaRepository.findAll();
    }

    public Asignatura save(Asignatura asignatura) {
        return this.asignaturaRepository.save(asignatura);
    }

    public Asignatura one(String id) {
        return this.asignaturaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id,"asignatura"));
    }

    public Asignatura replace(String id, Asignatura asignatura) {

        return this.asignaturaRepository.findById(id).map( p -> (id.equals(asignatura.getNombreAsignatura())  ?
                        this.asignaturaRepository.save(asignatura) : null))
                .orElseThrow(() -> new NotFoundException(id,"asignatura"));

    }

    public void delete(String id) {
        this.asignaturaRepository.findById(id).map(a -> {
                    a.getCursos().forEach(c -> c.getAsignaturas().remove(a));
                    a.getProfesores().forEach(p->p.getAsignaturas().remove(a));
                    this.asignaturaRepository.delete(a);
                    return a;})
                .orElseThrow(() -> new NotFoundException(id,"asignatura"));
    }
}
