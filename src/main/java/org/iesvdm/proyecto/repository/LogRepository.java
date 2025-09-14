package org.iesvdm.proyecto.repository;

import org.iesvdm.proyecto.model.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long>, JpaSpecificationExecutor<Log> {
    List<Log>   findAllByColor_Material_Product_CodeContainingIgnoreCaseAndLogDateBetweenAndAction(String colorMaterialProductCode, LocalDate logDateAfter, LocalDate logDateBefore, Log.Action action);
}

