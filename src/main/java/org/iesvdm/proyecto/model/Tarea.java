package org.iesvdm.proyecto.model;

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
        @UniqueConstraint(columnNames = {"tema_id", "nombre"})
})
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    long id;
    String nombre;
    @ManyToOne
    Profesor propietario;
    @ManyToOne
    @JsonIgnore
    Tema tema;
    @OneToMany(mappedBy = "tarea")
    private Set<TareaEstudiante> tareaEstudiantes;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    Fase basico;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    Fase intermedio;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    Fase avanzado;
    public String getRoute(){
        return this.tema.getRoute()+" "+this.nombre;
    }
}
