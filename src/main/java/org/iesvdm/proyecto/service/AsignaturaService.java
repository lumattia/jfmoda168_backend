package org.iesvdm.proyecto.service;

import org.iesvdm.proyecto.model.Asignatura;
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
    public List<Asignatura> allByFilter(String buscar) {
        return this.asignaturaRepository.findAsignaturasByNombreContainingIgnoreCase(buscar);
    }

    public Asignatura save(Asignatura asignatura) {
        return this.asignaturaRepository.save(asignatura);
    }

    public Asignatura one(long id) {
        return this.asignaturaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id,"asignatura"));
    }

    public Asignatura replace(long id, Asignatura asignatura) {

        return this.asignaturaRepository.findById(id).map( a -> (id==asignatura.getId())  ?
                        this.asignaturaRepository.save(asignatura) : null)
                .orElseThrow(() -> new NotFoundException(id,"asignatura"));
    }

    public void delete(long id) {
        this.asignaturaRepository.findById(id).map(a -> {
                    this.asignaturaRepository.delete(a);
                    return a;})
                .orElseThrow(() -> new NotFoundException(id,"asignatura"));
    }
}
