package org.iesvdm.proyecto.model.view;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FaseEstudiante {
    private long id;
    private List<PreguntaEstudiante> preguntas;
}
