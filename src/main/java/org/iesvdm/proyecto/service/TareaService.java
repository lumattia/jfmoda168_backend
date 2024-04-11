package org.iesvdm.proyecto.service;

import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.model.entity.Tarea;
import org.iesvdm.proyecto.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TareaService {
    private final TareaRepository tareaRepository;
    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }
    public Tarea save(Tarea tarea) {
        return this.tareaRepository.save(tarea);
    }
    public Tarea one(long id) {
        Optional<Tarea> t=this.tareaRepository.findById(id);
        return this.tareaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id,"tarea"));
    }

    public Tarea replace(long id, Tarea tarea) {

        return this.tareaRepository.findById(id).map( c -> (id==tarea.getId()  ?
                        this.tareaRepository.save(tarea) : null))
                .orElseThrow(() -> new NotFoundException(id,"tarea"));
    }
    public void delete(long id) {
        this.tareaRepository.findById(id).map(c -> {
                    this.tareaRepository.delete(c);
                    return c;})
                .orElseThrow(() -> new NotFoundException(id,"tarea"));
    }
}
