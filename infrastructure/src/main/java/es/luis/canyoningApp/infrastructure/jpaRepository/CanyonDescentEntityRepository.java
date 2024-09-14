package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.CanyonDescentEntity;
import org.springframework.data.repository.CrudRepository;

public interface CanyonDescentEntityRepository
        extends CrudRepository<CanyonDescentEntity, CanyonDescentEntity.PrimaryKeys> {
    void deleteByCanyonId(Long canyonId);
}
