package org.iesvdm.proyecto.model.view;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RespuestaEstudiante {
    private long id;
    private String respuesta;
    private boolean selected;
}
