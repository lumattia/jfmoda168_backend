package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.domain.Clase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaseRepository extends JpaRepository<Clase, Long> {
    List<Clase> findClaseByCurso_NombreContainingIgnoreCaseAndAsignatura_NombreContainingIgnoreCase(String curso,String Asignatura);
}
