package org.iesvdm.proyecto.mapper;

import org.iesvdm.proyecto.model.entity.*;
import org.iesvdm.proyecto.model.view.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    TareaFase tareaToTareaFase(Tarea tarea);
    TareaDetail tareaToTareaDetail(Tarea tarea);
    FaseEstudiante faseToFaseEstudiante(Fase fase);
    PreguntaEstudiante preguntaToPreguntaEstudiante(Pregunta pregunta);
    RespuestaEstudiante respuestaToRespuestaEstudiante(Respuesta respuesta);
    PuntoEstudiante tareaEstudianteToPuntoEstudiante(TareaEstudiante tareaEstudiante);
    PuntoTarea tareaEstudianteToPuntoTarea(TareaEstudiante tareaEstudiante);
}