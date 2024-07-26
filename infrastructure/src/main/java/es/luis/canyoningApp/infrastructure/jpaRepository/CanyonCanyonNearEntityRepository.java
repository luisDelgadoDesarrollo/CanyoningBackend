package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.CanyonCanyonNearEntity;
import org.springframework.data.repository.CrudRepository;

public interface CanyonCanyonNearEntityRepository
    extends CrudRepository<CanyonCanyonNearEntity, CanyonCanyonNearEntity.PrimaryKeys> {}
