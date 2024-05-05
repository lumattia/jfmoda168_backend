package org.iesvdm.proyecto.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.iesvdm.proyecto.model.view.ProfesorRow;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@DiscriminatorValue("PROFESOR")
public class Profesor extends Usuario{
    @ManyToMany(mappedBy = "profesores")
    @JsonIgnore
    Set<Clase> clases = new HashSet<>();
    @JsonIgnore
    @ManyToMany(mappedBy = "profesores")
    Set<Aula> aulas;
    public ProfesorRow toProfesorRow() {
        return new ProfesorRow() {
            @Override
            public long getId() {
                return Profesor.this.getId();
            }
            @Override
            public String getNombre() {
                return Profesor.this.getNombre();
            }
            @Override
            public String getApellidos() {
                return Profesor.this.getApellidos();
            }
            @Override
            public String getEmail() {
                return Profesor.this.getEmail();
            }
            @Override
            public boolean isBlocked() {
                return Profesor.this.isBlocked();
            }
        };
    }
}