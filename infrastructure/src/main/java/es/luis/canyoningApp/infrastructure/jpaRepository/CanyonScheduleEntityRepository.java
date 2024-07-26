package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.CanyonScheduleEntity;
import org.springframework.data.repository.CrudRepository;

public interface CanyonScheduleEntityRepository
    extends CrudRepository<CanyonScheduleEntity, CanyonScheduleEntity.PrimaryKeys> {}
