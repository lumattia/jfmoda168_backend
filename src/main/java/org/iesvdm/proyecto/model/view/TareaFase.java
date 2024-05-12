package org.iesvdm.proyecto.model.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.iesvdm.proyecto.model.entity.Fase;

import java.util.List;
@Data
@AllArgsConstructor
public class TareaFase {
    private long id;
    private String nombre;
    private List<Fase> fases;
}
