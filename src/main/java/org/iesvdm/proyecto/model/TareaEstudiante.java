package org.iesvdm.proyecto.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.iesvdm.proyecto.serializer.ClaseSerializer;
import org.iesvdm.proyecto.serializer.TareaEstudianteSerializer;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "tarea_estudiante")
@JsonSerialize(using = TareaEstudianteSerializer.class)
public class TareaEstudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Tarea tarea;
    @ManyToOne
    private Estudiante estudiante;
    private String fase;
    @Column(nullable = true)
    private int puntuacionBasica;
    @Column(nullable = true)
    private int puntuacionIntermedia;
    @Column(nullable = true)
    private int puntuacionAvanzada;

    // Constructor, getters y setters
}