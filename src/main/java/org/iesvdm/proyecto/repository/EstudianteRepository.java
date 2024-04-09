package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.model.Aula;
import org.iesvdm.proyecto.model.Estudiante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface EstudianteRepository extends JpaRepository<Estudiante,Long> {
    @Query("SELECT e FROM Estudiante e WHERE CONCAT(e.nombre, ' ', e.apellido1, ' ', e.apellido2) LIKE %?1%")
    Page<Estudiante> findByNombreCompleto(String nombreCompleto, Pageable pageable);
    @Query("SELECT a FROM Aula a JOIN a.estudiantes e WHERE e.id = ?1")
    Set<Aula> allAulas(Long estudianteId);
    Optional<Estudiante> findByEmail(String email);

}