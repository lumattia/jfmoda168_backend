package org.iesvdm.proyecto.service;

import org.iesvdm.proyecto.model.entity.User;
import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UsuarioRepository usuarioRepository;


    public UserService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public User one(String username) {
        return this.usuarioRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("未找到用户: " + username));
    }
    public void changePassword(String username,String codedNewPassword) {
        User usuario=one(username);
        usuario.setPassword(codedNewPassword);
        this.usuarioRepository.save(usuario);
    }
}
