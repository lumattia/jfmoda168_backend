package org.iesvdm.proyecto.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
        @UniqueConstraint(columnNames = {"asignatura_id", "curso_id"})
})
public class Clase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @OneToMany(mappedBy = "clase",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Aula> aulas;
    @ManyToOne(optional = false)
    private Asignatura asignatura;
    @ManyToOne(optional = false)
    private Curso curso;
    @ManyToMany
    @JsonIgnore
    private Set<Profesor> profesores;
    public String getNombre(){
        return this.curso.getNombre()+" "+this.asignatura.getNombre();
    }
}
