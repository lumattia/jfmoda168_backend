package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.model.entity.Aula;
import org.iesvdm.proyecto.model.entity.Profesor;
import org.iesvdm.proyecto.model.view.ProfesorRow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ProfesorRepository extends JpaRepository<Profesor,Long> {
    @Query("SELECT p FROM Profesor p WHERE CONCAT(p.nombre, ' ', p.apellido1, ' ', p.apellido2) LIKE %?1%")
    Page<ProfesorRow> findByNombreCompleto(String nombreCompleto, Pageable pageable);
    @Query("SELECT a FROM Aula a JOIN a.profesores p WHERE p.id = ?1")
    Set<Aula> allAulas(Long profesorId);
}
