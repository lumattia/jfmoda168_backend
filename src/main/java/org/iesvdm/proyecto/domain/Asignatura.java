package org.iesvdm.proyecto.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    long id;
    @Column(unique = true)
    String nombre;
    @OneToMany(mappedBy = "asignatura",cascade = CascadeType.ALL,orphanRemoval = true)
    @ToString.Exclude
    Set<Clase> clases;
}
