package org.iesvdm.proyecto.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class Fase implements Comparable<Fase>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    long id;
    @ManyToOne
    @JsonIgnore
    Tarea tarea;
    short nivel;
    String nombreArchivo;
    @OneToMany(mappedBy = "fase", cascade = CascadeType.ALL,orphanRemoval = true)
    List<Pregunta> preguntas;
    public Fase(Tarea tarea, int nivel){
        this.tarea=tarea;
        this.nivel=(short) nivel;
    }
    @Override
    public int compareTo(Fase o) {
        return this.nivel-o.nivel;
    }
}
