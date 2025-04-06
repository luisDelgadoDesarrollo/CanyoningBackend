package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.CanyonRappelingEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

public interface CanyonRappelingEntityRepository
        extends CrudRepository<CanyonRappelingEntity, CanyonRappelingEntity.PrimaryKeys> {
    @Modifying(clearAutomatically = true)
    void deleteByCanyonId(Long canyonId);
}
