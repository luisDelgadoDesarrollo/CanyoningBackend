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
            "SELECT sce FROM SimpleCanyonEntity sce "
                    + "LEFT JOIN CanyonLocationEntity cle ON sce.canyonId = cle.canyonId "
                    + "WHERE "
                    + "    (LOWER(sce.name) LIKE LOWER(CONCAT('%', :name, '%')) OR :name IS NULL) "
                    + "    AND (LOWER(sce.season) LIKE LOWER(CONCAT('%', :season, '%')) OR :season IS NULL) "
                    + "    AND (LOWER(sce.river) LIKE LOWER(CONCAT('%', :river, '%')) OR :river IS NULL) "
                    + "    AND (LOWER(cle.population) LIKE LOWER(CONCAT('%', :population, '%')) OR :population IS NULL) ")
    Page<SimpleCanyonEntity> getCanyons(
            String name, String season, String river, String population, Pageable pageable);
}
