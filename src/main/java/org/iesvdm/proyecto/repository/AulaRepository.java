package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.model.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface AulaRepository extends JpaRepository<Aula,Long> {

}
