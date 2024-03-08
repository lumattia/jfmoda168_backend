package org.iesvdm.proyecto.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.iesvdm.proyecto.serializer.ClaseSerializer;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@JsonSerialize(using = ClaseSerializer.class)
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"asignatura_id", "curso_id"})
})
public class Clase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @ManyToOne(optional = false)
    private Asignatura asignatura;

    @ManyToOne(optional = false)
    private Curso curso;
    @ManyToMany
    Set<Profesor> profesores;
}
