package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.domain.Clase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaseRepository extends JpaRepository<Clase, Long> {
    List<Clase> findClaseByCurso_IdAndAsignatura_Id(long curso,long Asignatura);
    List<Clase> findClaseByCurso_Id(long curso);
    List<Clase> findClaseByAsignatura_Id(long Asignatura);

}
