package org.iesvdm.proyecto.model.entity;

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
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"aula_id", "nombre"})
})
public class Tema implements Comparable<Tema>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    long id;
    String nombre;

    @ManyToOne
    @JsonIgnore
    Aula aula;
    @OneToMany(mappedBy = "tema",cascade = CascadeType.ALL,orphanRemoval = true)
    Set<Tarea> tareas=new HashSet<>();
    @JsonIgnore
    boolean eliminado;
    public String getRoute(){
        return this.aula.getNombre()+" "+this.nombre;
    }

    @Override
    public int compareTo(Tema o) {
        return this.nombre.compareTo(o.getNombre());
    }
}
