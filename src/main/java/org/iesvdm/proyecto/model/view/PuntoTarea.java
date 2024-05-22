package org.iesvdm.proyecto.model.view;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PuntoTarea {
    private Option tarea;
    private byte fase;
    private Double basico;
    private Double intermedio;
    private Double avanzado;
}