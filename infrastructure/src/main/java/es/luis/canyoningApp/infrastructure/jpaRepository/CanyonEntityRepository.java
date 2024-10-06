package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.CanyonEntity;
import org.springframework.data.repository.CrudRepository;

public interface CanyonEntityRepository extends CrudRepository<CanyonEntity, Long> {}
