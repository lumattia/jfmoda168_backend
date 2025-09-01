package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.model.entity.Color;
import org.iesvdm.proyecto.model.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}

