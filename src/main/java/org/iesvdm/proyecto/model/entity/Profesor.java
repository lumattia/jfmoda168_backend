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
public class Profesor extends Usuario implements ProfesorRow{
    @ManyToMany(mappedBy = "profesores")
    @JsonIgnore
    Set<Clase> clases = new HashSet<>();
    @JsonIgnore
    @ManyToMany(mappedBy = "profesores")
    Set<Aula> aulas;
}