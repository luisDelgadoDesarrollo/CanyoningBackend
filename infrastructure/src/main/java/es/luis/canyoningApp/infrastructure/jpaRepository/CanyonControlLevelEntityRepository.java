package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.CanyonControlLevelEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CanyonControlLevelEntityRepository
    extends CrudRepository<CanyonControlLevelEntity, CanyonControlLevelEntity.PrimaryKeys> {
  @Modifying(clearAutomatically = true)
  @Query("DELETE FROM CanyonControlLevelEntity ccle where ccle.canyon.canyonId = :canyonId")
  void deleteByCanyonId(Long canyonId);
}
