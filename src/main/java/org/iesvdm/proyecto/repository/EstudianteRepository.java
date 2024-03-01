package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.domain.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante,Long> {
}
