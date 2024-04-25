package org.iesvdm.proyecto.service;

import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.model.entity.Fase;
import org.iesvdm.proyecto.repository.FaseRepository;
import org.springframework.stereotype.Service;

@Service
public class FaseService {
    private final FaseRepository faseRepository;
    public FaseService(FaseRepository faseRepository) {
        this.faseRepository = faseRepository;
    }
    public Fase one(long tareaId,short nivel) {
        return this.faseRepository.findFaseByTarea_IdAndNivel(tareaId,nivel)
                .orElseThrow(() -> new NotFoundException("Fase not found"));
    }
}
