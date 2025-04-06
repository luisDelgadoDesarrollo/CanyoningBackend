package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.CanyonMessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CanyonMessageEntityRepository
        extends CrudRepository<CanyonMessageEntity, Long>,
        PagingAndSortingRepository<CanyonMessageEntity, Long> {

    @Query(
            "SELECT cme FROM CanyonMessageEntity cme where cme.canyon.canyonId = :canyonId order by cme.date desc")
    Page<CanyonMessageEntity> findAllByCanyon(Long canyonId, Pageable pageable);
}
