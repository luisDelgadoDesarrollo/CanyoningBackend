package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.CanyonEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CanyonEntityRepository extends CrudRepository<CanyonEntity, Long> {
  @Query("From CanyonEntity")
  List<CanyonEntity> findAllCanyons();
}
