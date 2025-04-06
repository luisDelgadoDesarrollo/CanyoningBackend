package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.CanyonReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CanyonReviewEntityRepository
        extends CrudRepository<CanyonReviewEntity, Long>,
        PagingAndSortingRepository<CanyonReviewEntity, Long> {

    @Query(
            "SELECT cre FROM CanyonReviewEntity cre WHERE (cre.canyon.canyonId = :canyonId or :canyonId is null)")
    Page<CanyonReviewEntity> getCanyonReviews(Long canyonId, Pageable pageable);
}
