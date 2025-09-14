package org.iesvdm.proyecto.service;

import org.iesvdm.proyecto.exeption.NotFoundException;
import org.iesvdm.proyecto.model.entity.Color;
import org.iesvdm.proyecto.model.entity.Log;
import org.iesvdm.proyecto.model.view.LogFilter;
import org.iesvdm.proyecto.repository.ColorRepository;
import org.iesvdm.proyecto.repository.LogRepository;
import org.iesvdm.proyecto.spec.LogSpecification;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {
    private final ColorRepository colorRepository;
    private final LogRepository logRepository;
    public LogService(ColorRepository colorRepository, LogRepository logRepository) {
        this.colorRepository = colorRepository;
        this.logRepository = logRepository;
    }
    public Page<Log> page(LogFilter filter, Pageable pageable) {
        Sort sort;
        String sortColumn = pageable.getSort().stream().findFirst()
                .map(Sort.Order::getProperty)
                .orElse("logDate");
        Sort.Direction direction = pageable.getSort().stream().findFirst()
                .map(Sort.Order::getDirection)
                .orElse(Sort.Direction.DESC);
        sort = switch (sortColumn) {
            case "productCode" -> Sort.by(direction, "color.material.product.code");
            case "materialName" -> Sort.by(direction, "color.material.name");
            default -> Sort.by(direction, sortColumn);
        };
        Pageable newPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        return logRepository.findAll(LogSpecification.filter(filter), newPageable);
    }
    public List<Log> getAllStat(LogFilter filter) {
        String code = filter.getProductCode() != null ? filter.getProductCode() : "";
        return  logRepository.findAllByColor_Material_Product_CodeContainingIgnoreCaseAndLogDateBetweenAndAction(code, filter.getDateFrom(), filter.getDateTo(), Log.Action.REMOVE);
    }
    public void undo(long logId) {
        Log log = logRepository.findById(logId).orElseThrow(() -> new NotFoundException("未找到该记录"));
        Color c = log.getColor();
        if (log.getAction() == Log.Action.ADD) {
            c.setStock(c.getStock() - log.getAmount());
        }else {
            c.setStock(c.getStock() + log.getAmount());
        }
        logRepository.delete(log);
        colorRepository.save(c);
    }
}
