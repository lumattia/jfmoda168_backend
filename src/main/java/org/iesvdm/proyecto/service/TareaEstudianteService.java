package org.iesvdm.proyecto.service;

import org.iesvdm.proyecto.model.entity.TareaEstudiante;
import org.iesvdm.proyecto.repository.TareaEstudianteRepository;
import org.springframework.stereotype.Service;

@Service
public class TareaEstudianteService {
    private final TareaEstudianteRepository tareaEstudianteRepository;

    public TareaEstudianteService(TareaEstudianteRepository tareaEstudianteRepository) {
        this.tareaEstudianteRepository = tareaEstudianteRepository;
    }
    public TareaEstudiante save(TareaEstudiante tareaEstudiante) {
        return this.tareaEstudianteRepository.save(tareaEstudiante);
    }
    public TareaEstudiante one(TareaEstudiante.TareaEstudianteId id) {
        return this.tareaEstudianteRepository.findById(id)
                .orElse(save(new TareaEstudiante(id)));
    }
    public TareaEstudiante replace(TareaEstudiante.TareaEstudianteId id, byte fase) {
        TareaEstudiante tareaEstudiante=one(id);
        if (fase>=1&&fase<=3)
            tareaEstudiante.setFase(fase);
        return tareaEstudiante;
    }

    public void delete(TareaEstudiante.TareaEstudianteId id) {
        this.tareaEstudianteRepository.delete(one(id));
    }
}
