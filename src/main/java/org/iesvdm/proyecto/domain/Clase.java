package org.iesvdm.proyecto.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"asignatura", "curso"})
})
public class Clase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn
    private Asignatura asignatura;

    @ManyToOne
    @JoinColumn
    private Curso curso;
    @ManyToMany
    Set<Profesor> profesores;
}
