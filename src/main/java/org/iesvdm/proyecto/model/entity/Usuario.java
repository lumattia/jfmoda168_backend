package org.iesvdm.proyecto.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@DiscriminatorColumn(name = "rol", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("ADMINISTRADOR")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(nullable = false)
    String nombre;
    @Column(nullable = false)
    String apellidos;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPassword() {
        return password;
    }
    public String getNombreCompleto() {
        return this.getNombre()+" "+getApellidos();
    }
}
