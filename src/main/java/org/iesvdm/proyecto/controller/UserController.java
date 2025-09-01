package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    PasswordEncoder encoder;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/changePassword")
    public void changePassword(@RequestBody Map<String, String> request) {
        String oldPassword = request.get("oldPassword");
        String newPassword = request.get("newPassword");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (encoder.matches(oldPassword,userService.one(auth.getName()).getPassword()))
            userService.changePassword(auth.getName(),encoder.encode(newPassword));
        else
            throw new AccessDeniedException("Contraseña incorrecta");
    }
}
