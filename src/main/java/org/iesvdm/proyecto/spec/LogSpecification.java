package org.iesvdm.proyecto.spec;

import jakarta.persistence.criteria.Predicate;
import org.iesvdm.proyecto.model.entity.Log;
import org.iesvdm.proyecto.model.view.LogFilter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class LogSpecification {

    public static Specification<Log> filter(LogFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getDateFrom() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("logDate"), filter.getDateFrom()));
            }

            if (filter.getDateTo() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("logDate"), filter.getDateTo()));
            }

            if (filter.getAdd() != null) {
                Log.Action accionValue = filter.getAdd() ? Log.Action.ADD : Log.Action.REMOVE;
                predicates.add(cb.equal(root.get("action"), accionValue));
            }

            if (filter.getColorName() != null) {
                predicates.add(cb.like(cb.lower(root.get("color").get("name")),
                        "%" + filter.getColorName().toLowerCase() + "%"));
            }

            if (filter.getMaterialName() != null) {
                predicates.add(cb.like(cb.lower(root.get("color").get("material").get("name")),
                        "%" + filter.getMaterialName().toLowerCase() + "%"));
            }

            if (filter.getProductCode() != null) {
                predicates.add(cb.like(cb.lower(root.get("color").get("material").get("product").get("code")),
                        "%" + filter.getProductCode().toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
