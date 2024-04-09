package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.model.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ClaseRepository extends JpaRepository<Clase, Long> {
    @Query("SELECT c FROM Clase c " +
            "WHERE (:cursoId =-1 OR c.curso.id = :cursoId) " +
            "AND (:asignaturaId =-1 OR c.asignatura.id = :asignaturaId)")
    Set<Clase> findFiltering(Long cursoId, Long asignaturaId);

}
