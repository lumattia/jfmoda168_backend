package org.iesvdm.proyecto.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.model.entity.Aula;
import org.iesvdm.proyecto.model.entity.Estudiante;
import org.iesvdm.proyecto.model.entity.Profesor;
import org.iesvdm.proyecto.model.entity.Tema;
import org.iesvdm.proyecto.model.view.EstudianteRow;
import org.iesvdm.proyecto.model.view.ProfesorRow;
import org.iesvdm.proyecto.repository.AulaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AulaService {
    @PersistenceContext
    EntityManager em;
    private final AulaRepository aulaRepository;

    public AulaService(AulaRepository aulaRepository) {
        this.aulaRepository = aulaRepository;
    }
    @Transactional
    public Aula save(Aula aula) {
        aula.getProfesores().add(aula.getPropietario());
        this.aulaRepository.save(aula);
        this.em.refresh(aula);
        return aula;
    }
    public Aula one(long id) {
        Aula a=this.aulaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id,"aula"));
        if (a.isEliminado()){
            throw new NotFoundException("Aula eliminada");
        }
        return a;
    }
    public Set<ProfesorRow> getProfesores(long id,String buscar) {
        return this.aulaRepository.getProfesores(id,buscar);
    }
    public Set<EstudianteRow> getEstudiantes(long id,String buscar) {
        return this.aulaRepository.getEstudiantes(id,buscar);
    }
    public Tema createTema(long id, Tema tema) {
        Aula a=one(id);
        tema.setAula(a);
        a.getTemas().add(tema);
        this.aulaRepository.save(a);
        return a.getTemas().stream().sorted((o1, o2) -> Math.toIntExact(o2.getId() - o1.getId())).toList().get(0);
    }
    public Set<ProfesorRow> addProf(long id, Set<Profesor> p) {
        Aula a=one(id);
        a.getProfesores().addAll(p);
        this.aulaRepository.save(a);
        return p.stream().map(Profesor::toProfesorRow).collect(Collectors.toSet());
    }
    public Set<EstudianteRow> addEst(long id, Set<Estudiante> e) {
        Aula a=one(id);
        a.getEstudiantes().addAll(e);
        this.aulaRepository.save(a);
        return e.stream().map(Estudiante::toEstudianteRow).collect(Collectors.toSet());

    }
    public Aula replace(long id, Aula aula) {
        Aula a= one(id);
        if (id==a.getId()){
            a.setGrupo(aula.getGrupo());
            a.setAnio(aula.getAnio());
            return this.aulaRepository.save(a);
        }
        return null;
    }

    public void delete(long id,Profesor prof) {
        Aula a=this.aulaRepository.findById(id).orElseThrow(() -> new NotFoundException(id,"aula"));
        if (a.getPropietario().equals(prof)){
            a.setEliminado(true);
            this.aulaRepository.save(a);
        }else{
            a.getProfesores().remove(prof);
            this.aulaRepository.save(a);
        }
    }
    public void removeProf(long id,long idProf) {
        Aula a=this.aulaRepository.findById(id).orElseThrow(() -> new NotFoundException(id,"aula"));
        Profesor p=new Profesor();
        p.setId(idProf);
        if (a.getProfesores().remove(p)){
            this.aulaRepository.save(a);
        }else{
            throw new NotFoundException("No se ha encontrado ese profesor en esa aula");
        }
    }
    public void removeEst(long id,long idEst) {
        Aula a=this.aulaRepository.findById(id).orElseThrow(() -> new NotFoundException(id,"aula"));
        Estudiante e=new Estudiante();
        e.setId(idEst);
        if (a.getEstudiantes().remove(e)){
            this.aulaRepository.save(a);
        }else{
            throw new NotFoundException("No se ha encontrado ese estudiante en esa aula");
        }
    }
}
