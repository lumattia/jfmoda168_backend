package org.iesvdm.proyecto.controller;

import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.model.entity.Material;
import org.iesvdm.proyecto.repository.MaterialRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.security.access.AccessDeniedException;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ExceptionController {
    private final MaterialRepository materialRepository;

    public ExceptionController(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleForbiddenException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class, DataIntegrityViolationException.class})
    public ResponseEntity<String> handleConstraintViolation(Exception e) {
        String message = e.getMessage();
        String userMessage = message;
        String name;
        if (message.contains("Duplicate entry")) {
            int start = message.indexOf("Duplicate entry '") + "Duplicate entry '".length();
            int end = message.indexOf("'", start);
            if (start > 0 && end > start) {
                String value = message.substring(start, end); // e.g. Cotton-3
                String[] parts = value.split("-");
                name = parts[0];
                if(message.contains("for key 'color")) {
                    if (parts.length > 1) {
                        var parentId = Long.valueOf(parts[1]);
                        Material parent = materialRepository.findById(parentId).orElse(null);
                        String parentName = parent != null ? parent.getName() : "布料";
                        userMessage = parentName + " 下的颜色 " + name + " 已存在，请使用其他名称。";
                    }
                }else{
                    userMessage = name + " 已存在，请使用其他名称。";
                }
            }
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(userMessage);

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}