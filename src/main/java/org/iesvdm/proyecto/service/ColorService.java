package org.iesvdm.proyecto.service;

import org.iesvdm.proyecto.model.entity.*;
import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.model.view.StockUpdateRequest;
import org.iesvdm.proyecto.repository.ColorRepository;
import org.iesvdm.proyecto.repository.LogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;


@Service
public class ColorService {
    private final ColorRepository colorRepository;
    private final LogRepository logRepository;
    public ColorService(ColorRepository colorRepository, LogRepository logRepository) {
        this.colorRepository = colorRepository;
        this.logRepository = logRepository;
    }
    public Color get(long colorId) {
        return colorRepository.findById(colorId).orElseThrow(() -> new NotFoundException("未找到该颜色"));
    }
    public void add(StockUpdateRequest request) {
        for (StockUpdateRequest.StockItem toAdd : request.getStock()) {
            Color c = get(toAdd.getId());
            c.setStock(c.getStock() + toAdd.getAmount());
            Log log = new Log();
            log.setColor(c);
            log.setAmount(toAdd.getAmount());
            log.setLogDate(Objects.requireNonNullElse(request.getDate(), LocalDate.now()));
            log.setAction(Log.Action.ADD);
            logRepository.save(log);
            colorRepository.save(c);
        }
    }
    public void remove(StockUpdateRequest request) {
        for (StockUpdateRequest.StockItem toRemove : request.getStock()) {
            Color c = get(toRemove.getId());
            Log log = new Log();
            log.setColor(c);
            log.setAmount(toRemove.getAmount());
            log.setLogDate(Objects.requireNonNullElse(request.getDate(), LocalDate.now()));
            log.setAction(Log.Action.REMOVE);
            logRepository.save(log);
            colorRepository.save(c);
        }
    }
}
