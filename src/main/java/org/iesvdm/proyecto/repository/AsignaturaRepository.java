package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.model.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsignaturaRepository extends JpaRepository<Asignatura,Long> {
    List<Asignatura> findAsignaturasByNombreContainingIgnoreCase(String buscar);
}
