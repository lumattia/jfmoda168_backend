package org.iesvdm.proyecto.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.iesvdm.proyecto.model.view.Option;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
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
    @JsonIgnore
    private TareaEstudianteId id;
    private byte fase=1;
    @Column
    private Double basico;
    @Column
    private Double intermedio;
    @Column
    private Double avanzado;
    public TareaEstudiante(TareaEstudianteId id){
        this.id=id;
    }
    public Option getEstudiante(){
        return new Option() {
            @Override
            public Long getId() {
                return TareaEstudiante.this.getId().getEstudiante().getId();
            }
            @Override
            public String getNombre() {
                return TareaEstudiante.this.getId().getEstudiante().getNombreCompleto();
            }
        };
    }
    public Option getTarea(){
        return new Option() {
            @Override
            public Long getId() {
                return TareaEstudiante.this.getId().getTarea().getId();
            }
            @Override
            public String getNombre() {
                return TareaEstudiante.this.getId().getTarea().getNombre();
            }
        };
    }
}