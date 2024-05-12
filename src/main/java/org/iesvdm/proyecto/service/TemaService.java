package org.iesvdm.proyecto.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.model.entity.Tarea;
import org.iesvdm.proyecto.model.entity.Tema;
import org.iesvdm.proyecto.repository.TemaRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TemaService {
    private final TemaRepository temaRepository;
    @PersistenceContext
    EntityManager em;
    public TemaService(TemaRepository temaRepository) {
        this.temaRepository = temaRepository;
    }
    public Tarea createTarea(Tema t, Tarea tarea) {
        tarea.setTema(t);
        t.getTareas().add(tarea);
        this.temaRepository.save(t);
        return t.getTareas().stream().sorted((o1, o2) -> Math.toIntExact(o2.getId() - o1.getId())).toList().get(0);
    }
    @Transactional
    public Set<Tarea> addTareas(Tema t, Set<Tarea> tareas) {
        tareas.forEach(tarea -> {
            em.detach(tarea);
            tarea.setId(0);
            tarea.setTema(t);
            tarea.setEliminado(false);
            tarea.setVisible(false);
            tarea.getFases().forEach(fase -> {
                em.detach(fase);
                fase.setId(0);
                fase.getPreguntas().forEach(pregunta -> {
                    em.detach(pregunta);
                    pregunta.setId(0);
                    pregunta.getRespuestas().forEach(respuesta -> {
                        em.detach(respuesta);
                        respuesta.setId(0);
                    });
                });
            });
            t.getTareas().add(tarea);
            this.temaRepository.save(t);
        });
        return new HashSet<>(t.getTareas());
    }
    public Tema one(long id) {
        return this.temaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id,"tema"));
    }

    public Tema replace(Tema t, Tema tema) {
        t.setNombre(tema.getNombre());
        return this.temaRepository.save(t);
    }

    public void delete(Tema t) {
            t.setEliminado(true);
            this.temaRepository.save(t);
    }
}
