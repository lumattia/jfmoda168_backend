package org.iesvdm.proyecto.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.model.entity.Aula;
import org.iesvdm.proyecto.model.entity.Tema;
import org.iesvdm.proyecto.repository.AulaRepository;
import org.springframework.stereotype.Service;

@Service
public class AulaService {
    @PersistenceContext
    EntityManager em;
    private final AulaRepository aulaRepository;
    public AulaService(AulaRepository aulaRepository) {
        this.aulaRepository = aulaRepository;
    }

    public Tema createTema(long id, Tema tema) {
        Aula a=one(id);
        tema.setAula(a);
        a.getTemas().add(tema);
        this.aulaRepository.save(a);
        return a.getTemas().stream().sorted((o1, o2) -> Math.toIntExact(o2.getId() - o1.getId())).toList().get(0);
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
            throw new RuntimeException("Aula eliminada");
        }
        return a;
    }

    public Aula replace(long id, Aula aula) {
        Aula a= one(id);
        if (id==a.getId()){
            a.setGrupo(aula.getGrupo());
            a.setAño(aula.getAño());
            return this.aulaRepository.save(a);
        }
        return null;
    }

    public void delete(long id,long usuarioId) {
        Aula a=this.aulaRepository.findById(id).orElseThrow(() -> new NotFoundException(id,"aula"));
        if (a.getPropietario().getId()==usuarioId){
            a.setEliminado(true);
            a.setProfesores(null);
            a.setEstudiantes(null);
            this.aulaRepository.save(a);
        }else{
            throw new RuntimeException("No tienes permiso para eliminar esa clase.");
        }
    }
}
