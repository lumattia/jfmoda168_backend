package org.iesvdm.proyecto.service;

import org.iesvdm.proyecto.domain.Estudiante;
import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.repository.EstudianteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {
    private final EstudianteRepository estudianteRepository;
    public EstudianteService(EstudianteRepository estudianteRepository ) {
        this.estudianteRepository = estudianteRepository;
    }

    public List<Estudiante> all() {
        return this.estudianteRepository.findAll();
    }
    public Page<Estudiante> allByFilter(String buscar, Pageable pageable) {
        Page<Estudiante> page=this.estudianteRepository.findEstudiantesByNombreContainingIgnoreCase(buscar,pageable);
        return page;
    }
    public Estudiante save(Estudiante estudiante) {
        return this.estudianteRepository.save(estudiante);
    }

    public Estudiante one(Long id) {
        return this.estudianteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id,"estudiante"));
    }

    public Estudiante replace(Long id, Estudiante estudiante) {

        return this.estudianteRepository.findById(id).map( p -> (id.equals(estudiante.getId())  ?
                        this.estudianteRepository.save(estudiante) : null))
                .orElseThrow(() -> new NotFoundException(id,"estudiante"));

    }

    public void delete(Long id) {
        this.estudianteRepository.findById(id).map(e -> {
                    this.estudianteRepository.delete(e);
                    return e;})
                .orElseThrow(() -> new NotFoundException(id,"estudiante"));
    }
}
