package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.model.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso,Long> {
    List<Curso> findCursosByNombreContainingIgnoreCase(String buscar);
}
