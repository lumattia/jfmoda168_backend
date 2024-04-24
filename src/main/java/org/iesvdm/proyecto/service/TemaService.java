package org.iesvdm.proyecto.service;

import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.model.entity.Tarea;
import org.iesvdm.proyecto.model.entity.Tema;
import org.iesvdm.proyecto.repository.TemaRepository;
import org.springframework.stereotype.Service;

@Service
public class TemaService {
    private final TemaRepository temaRepository;
    public TemaService(TemaRepository temaRepository) {
        this.temaRepository = temaRepository;
    }
    public Tarea createTarea(long id, Tarea tarea) {
        Tema t=one(id);
        tarea.setTema(t);
        t.getTareas().add(tarea);
        this.temaRepository.save(t);
        return t.getTareas().stream().sorted((o1, o2) -> Math.toIntExact(o2.getId() - o1.getId())).toList().get(0);
    }
    public Tema one(long id) {
        return this.temaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id,"tema"));
    }

    public Tema replace(long id, Tema tema) {
        Tema t=one(id);
        if (id==tema.getId()){
            t.setNombre(tema.getNombre());
            return this.temaRepository.save(t);
        }
        return null;
    }

    public void delete(long id) {
        this.temaRepository.findById(id).map(c -> {
                    this.temaRepository.delete(c);
                    return c;})
                .orElseThrow(() -> new NotFoundException(id,"tema"));
    }
}
