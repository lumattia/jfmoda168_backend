package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.model.entity.Fase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FaseRepository extends JpaRepository<Fase, Long> {
    @Query("SELECT f FROM Tarea t JOIN t.fases f WHERE t.id = ?1 and f.nivel=?2 ")
    Optional<Fase> findFaseByTarea(long id, short nivel);
}
