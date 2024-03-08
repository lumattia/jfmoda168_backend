package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.domain.Estudiante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante,Long> {
    public Page<Estudiante> findEstudiantesByNombreContainingIgnoreCase(String buscar, Pageable pageable);
}
