package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.CanyonProhibitionEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CanyonProhibitionEntityRepository
    extends CrudRepository<CanyonProhibitionEntity, Long> {
  @Modifying(clearAutomatically = true)
  @Query("DELETE FROM CanyonProhibitionEntity cpe where cpe.canyon.canyonId = :canyonId")
  void deleteByCanyonId(Long canyonId);
}
