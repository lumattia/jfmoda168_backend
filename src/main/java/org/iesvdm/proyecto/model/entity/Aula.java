package org.iesvdm.proyecto.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;
    private String grupo;
    @Column(nullable = false)
    private String año;
    @ManyToMany
    private Set<Profesor> profesores=new HashSet<>();
    @ManyToOne(optional = false)
    private Profesor propietario;
    @ManyToMany
    private Set<Estudiante> estudiantes;
    @ManyToOne(optional = false)
    private Clase clase;
    @OneToMany(mappedBy = "aula",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Tema> temas;
    private boolean eliminado;
    public String getNombre(){
        return this.clase.getNombre()+" "+this.grupo+" "+this.año;
    }
}
