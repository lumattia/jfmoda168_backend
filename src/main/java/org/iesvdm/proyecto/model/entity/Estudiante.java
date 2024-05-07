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
public class Estudiante extends Usuario implements EstudianteRow{
    String aula;
    @ManyToMany(mappedBy = "estudiantes")
    @JsonIgnore
    Set<Aula> aulas;
    @OneToMany(mappedBy = "id.estudiante")
    @JsonIgnore
    private Set<TareaEstudiante> tareaEstudiantes;
}
