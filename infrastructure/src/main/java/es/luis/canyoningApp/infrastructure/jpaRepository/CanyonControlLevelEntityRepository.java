package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.CanyonControlLevelEntity;
import org.springframework.data.repository.CrudRepository;

public interface CanyonControlLevelEntityRepository
        extends CrudRepository<CanyonControlLevelEntity, CanyonControlLevelEntity.PrimaryKeys> {
    void deleteByCanyonId(Long canyonId);
}
