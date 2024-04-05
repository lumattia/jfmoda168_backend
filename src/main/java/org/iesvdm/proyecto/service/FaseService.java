package org.iesvdm.proyecto.service;

import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.model.Fase;
import org.iesvdm.proyecto.repository.FaseRepository;
import org.springframework.stereotype.Service;

@Service
public class FaseService {
    private final FaseRepository faseRepository;
    public FaseService(FaseRepository faseRepository) {
        this.faseRepository = faseRepository;
    }
    public Fase one(long id) {
        return this.faseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id,"fase"));
    }
}
