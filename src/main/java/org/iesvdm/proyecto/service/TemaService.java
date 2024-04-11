package org.iesvdm.proyecto.service;

import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.model.entity.Tema;
import org.iesvdm.proyecto.repository.TemaRepository;
import org.springframework.stereotype.Service;

@Service
public class TemaService {
    private final TemaRepository temaRepository;
    public TemaService(TemaRepository temaRepository) {
        this.temaRepository = temaRepository;
    }
    public Tema save(Tema tema) {
        return this.temaRepository.save(tema);
    }
    public Tema one(long id) {
        return this.temaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id,"tema"));
    }

    public Tema replace(long id, Tema tema) {

        return this.temaRepository.findById(id).map( c -> (id==tema.getId()  ?
                        this.temaRepository.save(tema) : null))
                .orElseThrow(() -> new NotFoundException(id,"tema"));

    }

    public void delete(long id) {
        this.temaRepository.findById(id).map(c -> {
                    this.temaRepository.delete(c);
                    return c;})
                .orElseThrow(() -> new NotFoundException(id,"tema"));
    }
}
