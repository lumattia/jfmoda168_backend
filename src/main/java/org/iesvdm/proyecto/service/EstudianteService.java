package org.iesvdm.proyecto.service;

import org.iesvdm.proyecto.model.entity.Estudiante;
import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.model.view.EstudianteRow;
import org.iesvdm.proyecto.model.view.Option;
import org.iesvdm.proyecto.repository.EstudianteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EstudianteService {
    private final EstudianteRepository estudianteRepository;
    public EstudianteService(EstudianteRepository estudianteRepository ) {
        this.estudianteRepository = estudianteRepository;
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
                .orElseThrow(() -> new RuntimeException("Estudiante with Email: "+email+" not found"));
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
}
