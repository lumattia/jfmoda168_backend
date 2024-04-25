package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.model.entity.Aula;
import org.iesvdm.proyecto.model.view.EstudianteRow;
import org.iesvdm.proyecto.model.view.ProfesorRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface AulaRepository extends JpaRepository<Aula,Long> {
    @Query("SELECT p FROM Profesor p JOIN p.aulas a WHERE a.id = ?1 and CONCAT(p.nombre, ' ', p.apellidos) LIKE %?2%")
    Set<ProfesorRow> getProfesores(Long aulaId, String buscar);
    @Query("SELECT e FROM Estudiante e JOIN e.aulas a WHERE a.id = ?1 and CONCAT(e.nombre, ' ', e.apellidos) LIKE %?2%")
    Set<EstudianteRow> getEstudiantes(Long aulaId,String buscar);
}