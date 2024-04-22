package org.iesvdm.proyecto.service;

import org.iesvdm.proyecto.model.entity.Profesor;
import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.model.view.Option;
import org.iesvdm.proyecto.model.view.ProfesorRow;
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

    public Page<ProfesorRow> allByFilter(String buscar, Pageable pageable) {
        return this.profesorRepository.findByNombreCompleto(buscar,pageable);
    }
    public Set<Option> getAulas(Long id) {
        return this.profesorRepository.getAulas(id);
    }
    public Set<Option> getClases(Long id) {
        return this.profesorRepository.getClases(id);
    }
    public Profesor save(Profesor profesor) {
        return this.profesorRepository.save(profesor);
    }

    public Profesor one(Long id) {
        return this.profesorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id,"profesor"));
    }
    public Profesor one(String email) {
        return this.profesorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Profesor with Email: "+email+" not found"));
    }
    public Profesor replace(Long id, Profesor profesor) {
        Profesor p=this.profesorRepository.findById(id).orElseThrow(() -> new NotFoundException(id,"profesor"));
        p.setEmail(profesor.getEmail());
        p.setNombre(profesor.getNombre());
        p.setApellidos(profesor.getApellidos());
        profesorRepository.save(p);
        return p;
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
