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
    @OneToMany(mappedBy = "id.tarea",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    Set<TareaEstudiante> tareaEstudiantes=new HashSet<>();
    @OneToMany(mappedBy = "tarea",cascade = CascadeType.ALL,orphanRemoval = true)
    Set<Fase> fases;
    @JsonIgnore
    boolean eliminado;
    boolean visible;
    public String getRoute(){
        return this.tema.getRoute()+" "+this.nombre;
    }
    public String getPropietarioNombre() {
        return this.propietario.getNombre();
    }
    public String getTemaNombre() {
        return this.tema.getNombre();
    }
    public String getAulaGrupoAnio() {
        return this.tema.aula.getGrupo()+" "+this.tema.aula.getAnio();
    }
}
