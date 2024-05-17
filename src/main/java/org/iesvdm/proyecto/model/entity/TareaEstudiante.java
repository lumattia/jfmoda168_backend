package org.iesvdm.proyecto.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.iesvdm.proyecto.serializer.TareaEstudianteSerializer;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@JsonSerialize(using = TareaEstudianteSerializer.class)
public class TareaEstudiante {
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    @Data
    public static class TareaEstudianteId implements Serializable {
        @ManyToOne
        private Tarea tarea;
        @ManyToOne
        private Estudiante estudiante;
    }
    @EmbeddedId
    @EqualsAndHashCode.Include
    private TareaEstudianteId id;
    private byte fase=1;
    @Column
    private Double puntuacionBasica;
    @Column
    private Double puntuacionIntermedia;
    @Column
    private Double puntuacionAvanzada;
    public TareaEstudiante(TareaEstudianteId id){
        this.id=id;
    }
}