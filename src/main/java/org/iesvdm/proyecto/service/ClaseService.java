package org.iesvdm.proyecto.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.iesvdm.proyecto.domain.Clase;
import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.repository.ClaseRepository;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;

@Service
public class ClaseService {
    @PersistenceContext
    EntityManager em;
    private final ClaseRepository claseRepository;
    public ClaseService(ClaseRepository claseRepository) {
        this.claseRepository = claseRepository;
    }

    public List<Clase> all() {
        return this.claseRepository.findAll();
    }


    @Transactional
    public Clase save(Clase clase) {
        this.claseRepository.save(clase);
        this.em.refresh(clase);
        clase.setProfesores(new HashSet<>(em.createQuery("select C.profesores from Clase C where C.id = :id")
                .setParameter("id",clase.getId())
                .getResultList()));
        //NO PUEDO REFRESCAR COLECCION
        //this.em.refresh(clase.getProfesores());
        return clase;
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
