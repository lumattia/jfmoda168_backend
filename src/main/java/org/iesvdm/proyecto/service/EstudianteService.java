package org.iesvdm.proyecto.service;

import org.iesvdm.proyecto.domain.Estudiante;
import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.repository.EstudianteCustomRepository;
import org.iesvdm.proyecto.repository.EstudianteRepository;
import org.iesvdm.proyecto.util.ListToPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {
    private final EstudianteRepository estudianteRepository;
    private final EstudianteCustomRepository customRepository;
    public EstudianteService(EstudianteRepository estudianteRepository, EstudianteCustomRepository customRepository) {
        this.estudianteRepository = estudianteRepository;
        this.customRepository = customRepository;
    }

    public List<Estudiante> all() {
        return this.estudianteRepository.findAll();
    }
    public Page<Estudiante> allByFilter(Optional<String> buscar, Optional<String> order, int pagina, int tamaño) {
        Pageable p= PageRequest.of(pagina,tamaño);
        List<Estudiante> list=this.customRepository.queryCustomCategoria(buscar,order);
        Page<Estudiante> page= ListToPage.convertToPage(list,p);
        return page;
    }
    public Estudiante save(Estudiante estudiante) {
        return this.estudianteRepository.save(estudiante);
    }

    public Estudiante one(Long id) {
        return this.estudianteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id.toString(),"estudiante"));
    }

    public Estudiante replace(Long id, Estudiante estudiante) {

        return this.estudianteRepository.findById(id).map( p -> (id.equals(estudiante.getId())  ?
                        this.estudianteRepository.save(estudiante) : null))
                .orElseThrow(() -> new NotFoundException(id.toString(),"estudiante"));

    }

    public void delete(Long id) {
        this.estudianteRepository.findById(id).map(e -> {
                    this.estudianteRepository.delete(e);
                    return e;})
                .orElseThrow(() -> new NotFoundException(id.toString(),"estudiante"));
    }
}
