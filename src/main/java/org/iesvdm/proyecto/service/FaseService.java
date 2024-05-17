package org.iesvdm.proyecto.service;

import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.model.entity.Fase;
import org.iesvdm.proyecto.model.entity.Pregunta;
import org.iesvdm.proyecto.model.entity.Respuesta;
import org.iesvdm.proyecto.repository.FaseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class FaseService {
    private final FaseRepository faseRepository;
    public FaseService(FaseRepository faseRepository) {
        this.faseRepository = faseRepository;
    }
    public Fase one(long tareaId, short nivel) {
        return this.faseRepository.findFaseByTarea(tareaId,nivel)
                .orElseThrow(() -> new NotFoundException("Fase not found"));
    }
    public double entregar(List<Pregunta> preguntas, List<Long> respuestas) {
        int count=preguntas.size();
        AtomicInteger correct= new AtomicInteger();
        preguntas.forEach(p->{
            List<Long> selected=p.getRespuestas().stream().map(Respuesta::getId).filter(respuestas::contains).sorted().toList();
            List<Long> corrects=p.getRespuestas().stream().filter(Respuesta::isCorrecta).map(Respuesta::getId).sorted().toList();
            if (selected.equals(corrects)){
                correct.getAndIncrement();
            }
        });
        double resultado = (double) correct.intValue() / count * 10;
        BigDecimal resultadoRedondeado = new BigDecimal(resultado).setScale(2, RoundingMode.HALF_UP);

        return resultadoRedondeado.doubleValue();
    }
}
