package org.iesvdm.proyecto.mapper;

import org.iesvdm.proyecto.model.entity.Fase;
import org.iesvdm.proyecto.model.entity.Pregunta;
import org.iesvdm.proyecto.model.entity.Respuesta;
import org.iesvdm.proyecto.model.entity.Tarea;
import org.iesvdm.proyecto.model.view.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    TareaFase tareaToTareaFase(Tarea tarea);
    TareaDetail tareaToTareaDetail(Tarea tarea);
    FaseEstudiante faseToFaseEstudiante(Fase fase);
    PreguntaEstudiante preguntaToPreguntaEstudiante(Pregunta pregunta);
    RespuestaEstudiante respuestaToRespuestaEstudiante(Respuesta respuesta);
}