package org.iesvdm.proyecto.service;

import org.iesvdm.proyecto.model.Aula;
import org.iesvdm.proyecto.model.Profesor;
import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.repository.ClaseRepository;
import org.iesvdm.proyecto.repository.ProfesorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProfesorService {
    private final ProfesorRepository profesorRepository;
    private final ClaseRepository claseRepository;
    public ProfesorService(ProfesorRepository profesorRepository, ClaseRepository claseRepository) {
        this.profesorRepository = profesorRepository;
        this.claseRepository = claseRepository;
    }

    public Page<Profesor> all(Pageable pageable) {
        return this.profesorRepository.findAll(pageable);
    }
    public Page<Profesor> allByFilter(String buscar, Pageable pageable) {
        Page<Profesor> page=this.profesorRepository.findByNombreCompleto(buscar,pageable);
        return page;
    }
    public Set<Aula> allAulas(Long id) {
        return this.profesorRepository.allAulas(id);
    }

    public Profesor save(Profesor profesor) {
        return this.profesorRepository.save(profesor);
    }

    public Profesor one(Long id) {
        return this.profesorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id,"profesor"));
    }

    public Profesor replace(Long id, Profesor profesor) {

        return this.profesorRepository.findById(id).map( p -> (id.equals(profesor.getId())  ?
                        this.profesorRepository.save(profesor) : null))
                .orElseThrow(() -> new NotFoundException(id,"profesor"));

    }

    public void delete(Long id) {
        this.profesorRepository.findById(id).map(e -> {
                    e.getClases().forEach(clase -> {
                        clase.getProfesores().remove(e);
                        claseRepository.save(clase);
                    });
                    this.profesorRepository.delete(e);
                    return e;})
                .orElseThrow(() -> new NotFoundException(id,"profesor"));
    }
}
