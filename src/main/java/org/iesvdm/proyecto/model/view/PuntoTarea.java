package org.iesvdm.proyecto.model.view;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PuntoTarea {

    @Data
    @AllArgsConstructor
    public static class TareaDto {
        private Long id;
        private String nombreTema;
        private String nombre;
    }

    private TareaDto tarea;
    private byte fase;
    private Double basico;
    private Double intermedio;
    private Double avanzado;
}