package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.CanyonScheduleEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

public interface CanyonScheduleEntityRepository
        extends CrudRepository<CanyonScheduleEntity, CanyonScheduleEntity.PrimaryKeys> {
    @Modifying(clearAutomatically = true)
    void deleteByCanyonId(Long canyonId);
}
