package org.iesvdm.proyecto.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.iesvdm.proyecto.model.view.EstudianteRow;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
@Data
@DiscriminatorValue("ESTUDIANTE")
public class Estudiante extends Usuario{
    String aula;
    @ManyToMany(mappedBy = "estudiantes")
    @JsonIgnore
    Set<Aula> aulas;
    @OneToMany(mappedBy = "id.estudiante")
    @JsonIgnore
    private Set<TareaEstudiante> tareaEstudiantes;
    public EstudianteRow toEstudianteRow() {
        return new EstudianteRow() {
            @Override
            public long getId() {
                return Estudiante.this.getId();
            }
            @Override
            public String getNombre() {
                return Estudiante.this.getNombre();
            }
            @Override
            public String getApellidos() {
                return Estudiante.this.getApellidos();
            }
            @Override
            public String getEmail() {
                return Estudiante.this.getEmail();
            }
            @Override
            public String getAula() {
                return Estudiante.this.getAula();
            }
            @Override
            public boolean isBlocked() {
                return Estudiante.this.isBlocked();
            }
        };
    }
}
