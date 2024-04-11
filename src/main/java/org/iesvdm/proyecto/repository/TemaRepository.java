package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.model.entity.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemaRepository extends JpaRepository<Tema,Long> {
}
