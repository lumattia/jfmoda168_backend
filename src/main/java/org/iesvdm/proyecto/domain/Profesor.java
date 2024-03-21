package org.iesvdm.proyecto.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import org.iesvdm.proyecto.serializer.UsuarioSerializer;

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
}