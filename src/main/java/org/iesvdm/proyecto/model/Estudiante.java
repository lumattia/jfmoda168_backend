package org.iesvdm.proyecto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
@Data
@DiscriminatorValue("ESTUDIANTE")
public class Estudiante extends Usuario{
    String aula;
    @ManyToMany
    Set<Aula> aulas;
    @OneToMany(mappedBy = "estudiante")
    private Set<TareaEstudiante> tareaEstudiantes;
}
