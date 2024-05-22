package org.iesvdm.proyecto.model.view;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PuntoEstudiante {
    private Option estudiante;
    private byte fase;
    private Double basico;
    private Double intermedio;
    private Double avanzado;
}

