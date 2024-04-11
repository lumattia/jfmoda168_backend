package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.model.entity.TareaEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaEstudianteRepository extends JpaRepository<TareaEstudiante, TareaEstudiante.TareaEstudianteId> {
}
