package org.iesvdm.proyecto.service;

import org.iesvdm.proyecto.model.entity.Usuario;
import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;


    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Page<Usuario> all(Pageable pageable) {
        return this.usuarioRepository.findAll(pageable);
    }
    public Page<Usuario> allByFilter(String buscar, Pageable pageable) {
        return this.usuarioRepository.findByNombreCompleto(buscar,pageable);
    }
    public Usuario save(Usuario usuario) {
        return this.usuarioRepository.save(usuario);
    }
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario one(Long id) {
        return this.usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id,"usuario"));
    }

    public Usuario replace(Long id, Usuario usuario) {

        return this.usuarioRepository.findById(id).map(
                p -> (id.equals(usuario.getId()) && p.getRol().equals("ADMINISTRADOR") ?
                        this.usuarioRepository.save(usuario) : null))
                .orElseThrow(() -> new NotFoundException(id,"usuario"));

    }
    public boolean cambiarEstado(Long id) {
        Usuario usuario=one(id);
        usuario.setBlocked(!usuario.isBlocked());
        this.usuarioRepository.save(usuario);
        return usuario.isBlocked();

    }
    public void delete(Long id) {
        this.usuarioRepository.findById(id).map(e -> {
                    this.usuarioRepository.delete(e);
                    return e;})
                .orElseThrow(() -> new NotFoundException(id,"usuario"));
    }
}
