package org.iesvdm.proyecto.service;

import org.iesvdm.proyecto.model.Curso;
import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> all() {
        return this.cursoRepository.findAll();
    }
    public List<Curso> allByFilter(String buscar) {
        return this.cursoRepository.findCursosByNombreContainingIgnoreCase(buscar);
    }

    public Curso save(Curso curso) {
        return this.cursoRepository.save(curso);
    }

    public Curso one(long id) {
        return this.cursoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id,"curso"));
    }

    public Curso replace(long id, Curso curso) {

        return this.cursoRepository.findById(id).map( c -> (id==curso.getId()  ?
                        this.cursoRepository.save(curso) : null))
                .orElseThrow(() -> new NotFoundException(id,"curso"));

    }

    public void delete(long id) {
        this.cursoRepository.findById(id).map(c -> {
                    this.cursoRepository.delete(c);
                    return c;})
                .orElseThrow(() -> new NotFoundException(id,"curso"));
    }
}
