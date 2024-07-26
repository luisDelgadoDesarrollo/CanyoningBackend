package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.SimpleCanyonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SimpleCanyonEntityRepository
    extends CrudRepository<SimpleCanyonEntity, Long>,
        PagingAndSortingRepository<SimpleCanyonEntity, Long> {

  @Query(
      "SELECT sce FROM SimpleCanyonEntity sce WHERE (sce.name like :name or :name is null) and(sce.season like :season or :season is null) and (sce.river like :river or :river is null) and(sce.population like :population or :population is null)")
  Page<SimpleCanyonEntity> getCanyons(
      String name, String season, String river, String population, Pageable pageable);
}
