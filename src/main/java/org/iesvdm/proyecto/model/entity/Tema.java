package org.iesvdm.proyecto.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.iesvdm.proyecto.serializer.TemaSerializer;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"aula_id", "nombre"})
})
@JsonSerialize(using = TemaSerializer.class)
public class Tema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    long id;
    String nombre;

    @ManyToOne
    Aula aula;
    @OneToMany(mappedBy = "tema")
    Set<Tarea> tareas;

    long clase_id;

    public String getRoute(){
        return this.aula.getNombre()+" "+this.nombre;
    }
}
