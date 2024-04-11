package org.iesvdm.proyecto.service;

import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.model.entity.Aula;
import org.iesvdm.proyecto.model.entity.Tema;
import org.iesvdm.proyecto.repository.AulaRepository;
import org.springframework.stereotype.Service;

@Service
public class AulaService {
    private final AulaRepository aulaRepository;
    public AulaService(AulaRepository aulaRepository) {
        this.aulaRepository = aulaRepository;
    }
    public Aula createTema(long id, Tema tema) {
        Aula a=one(id);
        a.getTemas().add(tema);
        tema.setClase_id(a.getClase().getId());
        return this.aulaRepository.save(a);
    }
    public Aula save(Aula aula) {
        return this.aulaRepository.save(aula);
    }
    public Aula one(long id) {
        return this.aulaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id,"aula"));
    }

    public Aula replace(long id, Aula aula) {

        return this.aulaRepository.findById(id).map( c -> (id==aula.getId()  ?
                        this.aulaRepository.save(aula) : null))
                .orElseThrow(() -> new NotFoundException(id,"aula"));

    }

    public void delete(long id) {
        this.aulaRepository.findById(id).map(c -> {
                    this.aulaRepository.delete(c);
                    return c;})
                .orElseThrow(() -> new NotFoundException(id,"aula"));
    }
}
