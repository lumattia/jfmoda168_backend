package org.iesvdm.proyecto.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.iesvdm.proyecto.domain.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class EstudianteCustomRepositoryImpl implements EstudianteCustomRepository {
    @Autowired
    private EntityManager em;
    @Override
    public List<Estudiante> queryCustomCategoria(Optional<String> buscar, Optional<String> ordenar){
        StringBuilder queryBuilder=new StringBuilder("Select E from Estudiante E");
        if (buscar.isPresent()){
            queryBuilder.append(" where E.nombre like :buscar");
        }
        if (ordenar.isPresent() && (ordenar.get().equalsIgnoreCase("ASC")||ordenar.get().equalsIgnoreCase("DESC"))){
            queryBuilder.append(" order by E.nombre ").append(ordenar.get());
        }
        Query query=em.createQuery(queryBuilder.toString());
        buscar.ifPresent(s -> query.setParameter("buscar", "%" + s + "%"));
        return query.getResultList();
    }
}
