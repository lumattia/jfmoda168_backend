package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.model.entity.Color;
import org.iesvdm.proyecto.model.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}

