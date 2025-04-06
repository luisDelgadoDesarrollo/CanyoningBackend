package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.SimpleCanyonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SimpleCanyonEntityRepository
        extends CrudRepository<SimpleCanyonEntity, Long>,
        PagingAndSortingRepository<SimpleCanyonEntity, Long> {
}
