package org.iesvdm.proyecto.model.entity;

import jakarta.persistence.*;
import lombok.*;

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
    Set<Clase> clases = new HashSet<>();
    @ManyToMany
    Set<Aula> aulas;
}