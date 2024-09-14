package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.CanyonRappelingEntity;
import org.springframework.data.repository.CrudRepository;

public interface CanyonRappelingEntityRepository
        extends CrudRepository<CanyonRappelingEntity, CanyonRappelingEntity.PrimaryKeys> {
    void deleteByCanyonId(Long canyonId);
}
