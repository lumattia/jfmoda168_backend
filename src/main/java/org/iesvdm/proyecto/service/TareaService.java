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

    public Tarea one(long id) {
        return this.tareaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id,"tarea"));
    }
    public Tarea replace(Tarea t, Tarea tarea) {
        if (t.getId()==tarea.getId()){
            t.setFases(tarea.getFases());
            return this.tareaRepository.save(t);
        }
        return null;
    }
    public Tarea cambiarNombre(Tarea t, Tarea tarea) {
        if (t.getId()==tarea.getId()){
            t.setNombre(tarea.getNombre());
            return this.tareaRepository.save(t);
        }
        return null;
    }
    public boolean cambiarEstado(Tarea t) {
        t.setVisible(!t.getVisible());
        this.tareaRepository.save(t);
        return t.getVisible();
    }
    public void delete(Tarea t) {
        t.setEliminado(true);
        this.tareaRepository.save(t);
    }
}
