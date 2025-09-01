package org.iesvdm.proyecto.model.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
public class ColorDto {
    private Long id;
    private String name;
    private Integer stock;
    private String fileBase64;
    private String filename ;
}
