package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.CanyonProhibitionEntity;
import org.springframework.data.repository.CrudRepository;

public interface CanyonProhibitionEntityRepository
    extends CrudRepository<CanyonProhibitionEntity, Long> {}
