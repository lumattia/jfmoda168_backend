package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.model.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    @Query("SELECT u FROM Usuario u WHERE CONCAT(u.nombre, ' ', u.apellido1, ' ', u.apellido2) LIKE %?1%")
    Page<Usuario> findByNombreCompleto(String nombreCompleto, Pageable pageable);
    Optional<Usuario> findByEmail(String email);
    Boolean existsByEmail(String email);
}
