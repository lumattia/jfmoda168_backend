package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.model.entity.Usuario;
import org.iesvdm.proyecto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    PasswordEncoder encoder;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @GetMapping(value = {"","/"},params = {"!buscar"})
    public Page<Usuario> all(Pageable pageable) {
        log.info("Accediendo a todas los usuarios");
        return this.usuarioService.all(pageable);
    }
    @GetMapping({"","/"})
    public Page<Usuario> all(@RequestParam("buscar") String buscar,
                                Pageable pageable) {
        log.info("Accediendo a todas los usuarios");
        return this.usuarioService.allByFilter(buscar,pageable);
    }
    @PostMapping({"","/"})
    public Usuario save(@RequestBody Usuario usuario) {
        log.info("Guardando un usuario");
        return this.usuarioService.save(usuario);
    }
    @GetMapping("/{id}")
    public Usuario one(@PathVariable("id") long id) {
        return this.usuarioService.one(id);
    }
    @PutMapping("/{id}")
    public Usuario replace(@PathVariable("id") long id, @RequestBody Usuario usuario) {
        return this.usuarioService.replace(id, usuario);
    }
    @PutMapping("/cambiarEstado/{id}")
    public boolean cambiarEstado(@PathVariable("id") long id) {
        return this.usuarioService.cambiarEstado(id);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/cambiarContrasena/")
    public void cambiarContrasena(@RequestBody Map<String, String> request) {
        String oldPassword = request.get("oldPassword");
        String newPassword = request.get("newPassword");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (encoder.matches(oldPassword,usuarioService.one(auth.getName()).getPassword()))
            usuarioService.cambiarContraseña(auth.getName(),encoder.encode(newPassword));
        else
            throw new AccessDeniedException("Contraseña incorrecta");
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        this.usuarioService.delete(id);
    }

}
