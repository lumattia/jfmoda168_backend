package org.iesvdm.proyecto.mapper;

import org.iesvdm.proyecto.model.entity.Tarea;
import org.iesvdm.proyecto.model.view.TareaDetail;
import org.iesvdm.proyecto.model.view.TareaFase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    TareaFase tareaToTareaFase(Tarea tarea);
    TareaDetail tareaToTareaDetail(Tarea tarea);
}