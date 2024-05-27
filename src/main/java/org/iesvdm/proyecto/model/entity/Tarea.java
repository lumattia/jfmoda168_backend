package org.iesvdm.proyecto.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.iesvdm.proyecto.model.view.TareaRow;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class Tarea implements TareaRow,Comparable<Tarea> {
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
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "tarea_id")
    List<Fase> fases=new ArrayList<>();
    @JsonIgnore
    boolean eliminado;
    Boolean visible=false;
    public String getRoute(){
        return this.tema.getRoute()+" "+this.nombre;
    }
    @JsonProperty("propietario")
    public String getPropietarioNombreCompleto() {
        return this.propietario.getNombreCompleto();
    }
    public String getTemaNombre() {
        return this.tema.getNombre();
    }
    public String getAulaGrupoAnio() {
        return this.tema.aula.getGrupo()+" "+this.tema.aula.getAnio();
    }

    @Override
    public int compareTo(Tarea o) {
        return this.nombre.compareTo(o.getNombre());
    }
}
