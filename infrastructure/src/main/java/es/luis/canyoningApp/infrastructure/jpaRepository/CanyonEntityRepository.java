package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.CanyonEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CanyonEntityRepository extends CrudRepository<CanyonEntity, Long> {
  @Query("From CanyonEntity")
  List<CanyonEntity> findAllCanyons();

  @Query(
      "SELECT ce FROM CanyonEntity ce "
          + "WHERE "
          + "    (LOWER(ce.name) LIKE LOWER(CONCAT('%', :name, '%')) OR :name IS NULL) "
          + "    AND (LOWER(ce.season) LIKE LOWER(CONCAT('%', :season, '%')) OR :season IS NULL) "
          + "    AND (LOWER(ce.river) LIKE LOWER(CONCAT('%', :river, '%')) OR :river IS NULL) "
          + "    AND (LOWER(ce.location.population) LIKE LOWER(CONCAT('%', :population, '%')) OR :population IS NULL) ")
  Page<CanyonEntity> getCanyons(
      String name, String season, String river, String population, Pageable pageable);
}
