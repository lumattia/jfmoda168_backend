package org.iesvdm.proyecto.service;

import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.model.entity.Tarea;
import org.iesvdm.proyecto.repository.TareaRepository;
import org.springframework.stereotype.Service;


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
        return this.tareaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id,"tarea"));
    }

    public Tarea replace(long id, Tarea tarea) {
        Tarea t=one(id);
        if (id==tarea.getId()){
            t.setNombre(tarea.getNombre());
            return this.tareaRepository.save(t);
        }
        return null;
    }
    public void delete(long id) {
        this.tareaRepository.findById(id).map(t -> {
            t.setEliminado(true);
            this.tareaRepository.save(t);
            return t;
        }).orElseThrow(() -> new NotFoundException(id,"tarea"));
    }
}
