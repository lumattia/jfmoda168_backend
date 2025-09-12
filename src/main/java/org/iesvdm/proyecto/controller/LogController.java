package org.iesvdm.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto.model.entity.Log;
import org.iesvdm.proyecto.model.view.LogFilter;
import org.iesvdm.proyecto.model.view.LogPageDto;
import org.iesvdm.proyecto.service.LogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/api/logs")
public class LogController {
    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/page")
    public Page<LogPageDto> page(@ModelAttribute LogFilter filter, Pageable pageable) {
        Page<Log> logs =  logService.page(filter, pageable);
        return logs.map(log -> new LogPageDto(
                log.getId(),
                log.getLogDate(),
                log.getAction(),
                log.getAmount(),
                log.getColor().getMaterial().getProduct().getCode(),
                log.getColor().getMaterial().getName(),
                log.getColor().getName(),
                log.getColor().getPhotoPath() // 如果有图片
        ));
    }

    @DeleteMapping("{id}/undo")
    public void undo(@PathVariable("id") long id) {
        logService.undo(id);
    }
}
