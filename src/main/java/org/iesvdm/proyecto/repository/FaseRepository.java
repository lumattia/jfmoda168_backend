package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.model.entity.Fase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FaseRepository extends JpaRepository<Fase, Long> {
    Optional<Fase> findFaseByTarea_IdAndNivel(long id, short nivel);
}
