package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.CanyonCanyonNearEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CanyonCanyonNearEntityRepository
        extends CrudRepository<CanyonCanyonNearEntity, CanyonCanyonNearEntity.PrimaryKeys> {
    @Modifying
    @Query("DELETE FROM CanyonCanyonNearEntity ccne where ccne.canyon.canyonId = :canyonId")
    void deleteByCanyonId(Long canyonId);
}
