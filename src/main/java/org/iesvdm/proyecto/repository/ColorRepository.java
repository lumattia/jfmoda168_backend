package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.model.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Long> {
}

