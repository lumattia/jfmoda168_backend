package org.iesvdm.proyecto.repository;

import jakarta.transaction.Transactional;
import org.iesvdm.proyecto.model.entity.Clase;
import org.iesvdm.proyecto.model.entity.Profesor;
import org.iesvdm.proyecto.model.entity.Tema;
import org.iesvdm.proyecto.model.view.Option;
import org.iesvdm.proyecto.model.view.ProfesorRow;
import org.iesvdm.proyecto.model.view.TareaRow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Transactional
    @Query("SELECT DISTINCT p FROM Clase c " +
            "JOIN c.aulas a " +
            "JOIN a.temas te " +
            "JOIN te.tareas ta " +
            "JOIN ta.propietario p " +
            "WHERE c.id = :claseId")
    Set<Profesor> getProfesoresWithTarea(Long claseId);
    @Query("SELECT te FROM Tema te " +
            "JOIN te.aula a " +
            "WHERE a.id=:aulaId")
    Set<Tema> getTemas(Long aulaId);
    @Query("SELECT ta FROM Tarea ta " +
            "WHERE :claseId=ta.tema.aula.clase.id " +
            "AND (:aulaId =-1 OR ta.tema.aula.id = :aulaId)"+
            "AND (:temaId =-1 OR ta.tema.id = :temaId)"+
            "AND (:profesorId =-1 OR ta.propietario.id = :profesorId)")
    Page<TareaRow> getTareas(Long claseId, Long aulaId, Long temaId, Long profesorId, Pageable pageable);
    @Query("SELECT a FROM Aula a JOIN a.clase c WHERE c.id = ?1 and CONCAT(a.grupo, ' ', a.anio) LIKE %?2%")
    Set<Option> getAulas(Long claseId, String buscar);
}

