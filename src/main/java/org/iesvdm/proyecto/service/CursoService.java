package org.iesvdm.proyecto.service;

import org.iesvdm.proyecto.domain.Curso;
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

    public Curso save(Curso curso) {
        return this.cursoRepository.save(curso);
    }

    public Curso one(String id) {
        return this.cursoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id,"curso"));
    }

    public Curso replace(String id, Curso curso) {

        return this.cursoRepository.findById(id).map( p -> (id.equals(curso.getNombreCurso())  ?
                        this.cursoRepository.save(curso) : null))
                .orElseThrow(() -> new NotFoundException(id,"curso"));

    }

    public void delete(String id) {
        this.cursoRepository.findById(id).map(c -> {
                    c.getAsignaturas().forEach(a -> a.getCursos().remove(c));
                    this.cursoRepository.delete(c);
                    return c;})
                .orElseThrow(() -> new NotFoundException(id,"curso"));
    }
}
