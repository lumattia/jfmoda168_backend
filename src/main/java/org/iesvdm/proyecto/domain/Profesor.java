package org.iesvdm.proyecto.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import org.iesvdm.proyecto.serializer.ProfesorSerializer;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@JsonSerialize(using = ProfesorSerializer.class)
public class Profesor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    String nombre;
    String apellido1;
    String apellido2;
    @ManyToMany(mappedBy = "profesores")
    Set<Clase> clases = new HashSet<>();
}
