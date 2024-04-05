package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.model.Asignatura;
import org.iesvdm.proyecto.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemaRepository extends JpaRepository<Tema,Long> {
}
