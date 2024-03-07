package org.iesvdm.proyecto.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Curso {
    @Id
    @EqualsAndHashCode.Include
    String nombre;
    @OneToMany(mappedBy = "curso",cascade = CascadeType.ALL,orphanRemoval = true)
    Set<Clase> clases;
}
