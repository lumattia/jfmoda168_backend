package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.model.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TareaRepository extends JpaRepository<Tarea,Long> {
    @Query("SELECT t from Tarea t left join fetch t.fases f left join fetch f.preguntas p left join fetch p.respuesta r where t.id=:id")
    Optional<Tarea> findByIdIncluding(long id);
}
