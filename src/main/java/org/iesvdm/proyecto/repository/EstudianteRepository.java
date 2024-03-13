package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.domain.Estudiante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EstudianteRepository extends JpaRepository<Estudiante,Long> {
    @Query("SELECT e FROM Estudiante e WHERE e.nombre LIKE %:buscar% OR e.apellido1 LIKE %:buscar% OR e.apellido2 LIKE %:buscar%")
    Page<Estudiante> buscarEstudiantesPorNombreApellido(String buscar, Pageable pageable);}
