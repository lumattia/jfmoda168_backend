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
        @UniqueConstraint(columnNames = {"aula_id", "nombre"})
})
public class Tema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    long id;
    String nombre;

    @ManyToOne
    @JsonIgnore
    Aula aula;
    @OneToMany(mappedBy = "tema")
    Set<Tarea> tarea;

    @JsonIgnore
    long clase_id;

    public String getRoute(){
        return this.aula.getRoute()+" "+this.nombre;
    }
}
