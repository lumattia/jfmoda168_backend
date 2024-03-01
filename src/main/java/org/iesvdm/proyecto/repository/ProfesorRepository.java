package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor,Long> {
}
