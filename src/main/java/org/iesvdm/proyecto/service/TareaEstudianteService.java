package org.iesvdm.proyecto.service;

import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.model.TareaEstudiante;
import org.iesvdm.proyecto.repository.TareaEstudianteRepository;
import org.iesvdm.proyecto.repository.TareaEstudianteRepository;

public class TareaEstudianteService {


    private final TareaEstudianteRepository tareaEstudianteRepository;

    public TareaEstudianteService(TareaEstudianteRepository tareaEstudianteRepository) {
        this.tareaEstudianteRepository = tareaEstudianteRepository;
    }
    public TareaEstudiante save(TareaEstudiante tareaEstudiante) {
        return this.tareaEstudianteRepository.save(tareaEstudiante);
    }
    public TareaEstudiante one(long id) {
        return this.tareaEstudianteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id,"tareaEstudiante"));
    }

    public TareaEstudiante replace(long id, TareaEstudiante tareaEstudiante) {

        return this.tareaEstudianteRepository.findById(id).map( c -> (id==tareaEstudiante.getId()  ?
                        this.tareaEstudianteRepository.save(tareaEstudiante) : null))
                .orElseThrow(() -> new NotFoundException(id,"tareaEstudiante"));

    }

    public void delete(long id) {
        this.tareaEstudianteRepository.findById(id).map(c -> {
                    this.tareaEstudianteRepository.delete(c);
                    return c;})
                .orElseThrow(() -> new NotFoundException(id,"tareaEstudiante"));
    }
}
