package org.iesvdm.proyecto.controller;

import jakarta.validation.Valid;
import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.model.entity.Usuario;
import org.iesvdm.proyecto.security.TokenUtils;
import org.iesvdm.proyecto.model.entity.UserDetailsImpl;
import org.iesvdm.proyecto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/v1/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    TokenUtils tokenUtils;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> authenticateUser(@Valid @RequestBody Usuario usuario) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenUtils.generateToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();

        response.put("token", token);
        response.put("content", userDetails);
        response.put("roles", roles);

        return ResponseEntity.ok(response);

    }

    @PostMapping("/crearAdmin")
    public ResponseEntity<?> registerUser() {
        try{
            usuarioService.one(1L);
            return ResponseEntity.badRequest().body("Error: no se ha podido crear el usuario administrador");
        }catch (NotFoundException f){
            Usuario u=new Usuario();
            u.setId(1L);
            u.setEmail("admin@g.educaand.es");
            u.setNombre("admin");
            u.setApellidos("admin");
            u.setPassword(encoder.encode("123456"));
            usuarioService.save(u);
            return ResponseEntity.ok("Usuario registrado correctamente!");
        }
    }
}

