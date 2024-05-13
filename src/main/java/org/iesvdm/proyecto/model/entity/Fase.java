package org.iesvdm.proyecto.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    short nivel;
    String nombreArchivo;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "fase_id")
    List<Pregunta> preguntas=new ArrayList<>();
    public Fase(int nivel){
        this.nivel=(short) nivel;
    }
    @Override
    public int compareTo(Fase o) {
        return this.nivel-o.nivel;
    }
}
