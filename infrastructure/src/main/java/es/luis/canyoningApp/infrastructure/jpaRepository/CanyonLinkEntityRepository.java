package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.CanyonLinkEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CanyonLinkEntityRepository extends CrudRepository<CanyonLinkEntity, Long> {
  @Modifying(clearAutomatically = true)
  @Query("DELETE FROM CanyonLinkEntity cle where cle.canyon.canyonId = :canyonId")
  void deleteByCanyonId(Long canyonId);
}
