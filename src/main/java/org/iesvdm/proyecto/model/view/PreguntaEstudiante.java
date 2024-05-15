package org.iesvdm.proyecto.model.view;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PreguntaEstudiante {
    private long id;
    private String enunciado;
    private List<RespuestaEstudiante> respuestas;
}