package org.iesvdm.proyecto.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class Asignatura {
    @Id
    @EqualsAndHashCode.Include
    String nombre;
    @OneToMany(mappedBy = "asignatura",cascade = CascadeType.ALL,orphanRemoval = true)
    Set<Clase> clases;
}
