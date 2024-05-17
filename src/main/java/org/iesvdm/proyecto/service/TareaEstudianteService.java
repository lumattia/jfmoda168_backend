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
        return this.tareaEstudianteRepository.findById(id).orElseGet(() -> save(new TareaEstudiante(id)));
    }
    public TareaEstudiante replace(TareaEstudiante.TareaEstudianteId id, byte fase) {
        TareaEstudiante tareaEstudiante=one(id);
        if (fase>=1&&fase<=3)
            tareaEstudiante.setFase(fase);
        return tareaEstudiante;
    }
    public void saveResult(TareaEstudiante t, byte nivel, double result) {
        if (result>=5 && t.getFase()==nivel && nivel<3){
            t.setFase((byte) (t.getFase()+1));
        }
        switch (nivel){
            case 1:
                if (t.getPuntuacionBasica()==null||t.getPuntuacionBasica()<result)
                    t.setPuntuacionBasica(result);
                break;
            case 2:
                if (t.getPuntuacionIntermedia()==null||t.getPuntuacionIntermedia()<result)
                    t.setPuntuacionIntermedia(result);
                break;
            case 3:
                if (t.getPuntuacionAvanzada()==null||t.getPuntuacionAvanzada()<result)
                    t.setPuntuacionAvanzada(result);
                break;
        }
        tareaEstudianteRepository.save(t);
    }
    public void delete(TareaEstudiante.TareaEstudianteId id) {
        this.tareaEstudianteRepository.delete(one(id));
    }
}
