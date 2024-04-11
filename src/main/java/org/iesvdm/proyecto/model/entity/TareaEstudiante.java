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
@Table(name = "tarea_estudiante",
        uniqueConstraints={
        @UniqueConstraint(columnNames = {"estudiante_id", "tarea_id"})
})
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
    private TareaEstudianteId id;
    private byte fase=1;
    @Column
    private Double puntuacionBasica=null;
    @Column
    private Double puntuacionIntermedia=null;
    @Column
    private Double puntuacionAvanzada=null;
    public TareaEstudiante(TareaEstudianteId id){
        this.id=id;
    }
}