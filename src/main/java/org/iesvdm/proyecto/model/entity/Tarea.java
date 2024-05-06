package org.iesvdm.proyecto.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.iesvdm.proyecto.model.view.TareaDetail;
import org.iesvdm.proyecto.model.view.TareaRow;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class Tarea implements TareaRow,TareaDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    long id;
    String nombre;
    Boolean visible;
    @ManyToOne
    Profesor propietario;
    @ManyToOne
    @JsonIgnore
    Tema tema;
    @OneToMany(mappedBy = "id.tarea",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    Set<TareaEstudiante> tareaEstudiantes=new HashSet<>();
    @OneToMany(mappedBy = "tarea",cascade = CascadeType.ALL,orphanRemoval = true)
    Set<Fase> fases=new HashSet<>();
    @JsonIgnore
    boolean eliminado;
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

}
