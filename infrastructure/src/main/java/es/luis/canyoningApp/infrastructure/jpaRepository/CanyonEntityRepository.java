package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.CanyonEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CanyonEntityRepository extends CrudRepository<CanyonEntity, Long> {
  @Query(value = "SELECT MAX(ce.canyonId) + 1 FROM CanyonEntity ce")
  Long getNextId();
}
