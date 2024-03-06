package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.domain.Estudiante;

import java.util.List;
import java.util.Optional;

public interface EstudianteCustomRepository {
    public List<Estudiante> queryCustomCategoria(Optional<String> buscar, Optional<String> ordenar);
    }
