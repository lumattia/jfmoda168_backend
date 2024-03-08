package org.iesvdm.proyecto.service;

import org.iesvdm.proyecto.domain.Clase;
import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.repository.ClaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClaseService {
    private final ClaseRepository claseRepository;
    public ClaseService(ClaseRepository claseRepository) {
        this.claseRepository = claseRepository;
    }

    public List<Clase> all() {
        return this.claseRepository.findAll();
    }

    public Clase save(Clase clase) {
        return this.claseRepository.save(clase);
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

    public void delete(Long id) {
        this.claseRepository.findById(id).map(c -> {
                    this.claseRepository.delete(c);
                    return c;})
                .orElseThrow(() -> new NotFoundException(id,"clase"));
    }

}
