package org.iesvdm.proyecto.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.iesvdm.proyecto.serializer.UsuarioSerializer;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@DiscriminatorColumn(name = "rol", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("ADMINISTRADOR")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonSerialize(using = UsuarioSerializer.class)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(nullable = false)
    String nombre;
    @Column(nullable = false)
    String apellido1;
    String apellido2="";
    @Column(nullable = false,unique = true)
    @Email(regexp = "^[a-z0-9]+@g.educaand.es$")
    String email;
    @Column(nullable = false)//password="123456" encrypted:$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK
    String password;
    boolean blocked=false;
    public String getRol() {
        DiscriminatorValue discriminatorValue = getClass().getAnnotation(DiscriminatorValue.class);
        return discriminatorValue.value();
    }
    public String getNombreCompleto() {
        return this.getNombre()+" "+this.getApellido1()+" "+this.getApellido2();
    }
}
