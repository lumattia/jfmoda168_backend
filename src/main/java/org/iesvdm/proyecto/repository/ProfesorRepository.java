package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.domain.Profesor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfesorRepository extends JpaRepository<Profesor,Long> {
    @Query("SELECT p FROM Profesor p WHERE CONCAT(p.nombre, ' ', p.apellido1, ' ', p.apellido2) LIKE %?1%")
    Page<Profesor> findByNombreCompleto(String nombreCompleto, Pageable pageable);
}
