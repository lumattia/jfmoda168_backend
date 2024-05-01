package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.model.entity.Clase;
import org.iesvdm.proyecto.model.view.Option;
import org.iesvdm.proyecto.model.view.ProfesorRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;
public interface ClaseRepository extends JpaRepository<Clase, Long> {
    @Query("SELECT c FROM Clase c " +
            "WHERE (:cursoId =-1 OR c.curso.id = :cursoId) " +
            "AND (:asignaturaId =-1 OR c.asignatura.id = :asignaturaId)")
    Set<Option> getButtonsFiltering(Long cursoId, Long asignaturaId);
    @Query("SELECT p FROM Profesor p JOIN p.clases c WHERE c.id = ?1 and CONCAT(p.nombre, ' ', p.apellidos) LIKE %?2%")
    Set<ProfesorRow> getProfesores(Long aulaId, String buscar);
    @Query("SELECT DISTINCT p FROM Profesor p " +
            "JOIN p.aulas a " +
            "JOIN a.temas te " +
            "JOIN te.tareas ta " +
            "JOIN ta.propietario pr " +
            "JOIN a.clase c " +
            "WHERE c.id = :claseId and pr=p")
    Set<ProfesorRow> getProfesoresWithTareaInClass(Long claseId);
    @Query("SELECT a FROM Aula a JOIN a.clase c WHERE c.id = ?1")
    Set<Option> getAllAulas(Long claseId);
    @Query("SELECT a FROM Aula a JOIN a.clase c WHERE c.id = ?1 and CONCAT(a.grupo, ' ', a.anio) LIKE %?2%")
    Set<Option> getAulas(Long claseId, String buscar);
}
