package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.model.entity.Profesor;
import org.iesvdm.proyecto.model.view.Option;
import org.iesvdm.proyecto.model.view.ProfesorRow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface ProfesorRepository extends JpaRepository<Profesor,Long> {
    @Query("SELECT p FROM Profesor p WHERE CONCAT(p.nombre, ' ', p.apellidos) LIKE %?1% and p.blocked = false")
    Page<ProfesorRow> findSearchNotBlocked(String nombreCompleto, Pageable pageable);
    @Query("SELECT p FROM Profesor p WHERE CONCAT(p.nombre, ' ', p.apellidos) LIKE %?1%")
    Page<ProfesorRow> findByNombreCompleto(String nombreCompleto, Pageable pageable);
    @Query("SELECT a FROM Aula a JOIN a.profesores p WHERE p.id = ?1 and a.eliminado=false ")
    Set<Option> getAulas(Long profesorId);
    @Query("SELECT c FROM Clase c JOIN c.profesores p WHERE p.id = ?1")
    Set<Option> getClases(Long profesorId);
    Optional<Profesor> findByEmail(String email);
}
