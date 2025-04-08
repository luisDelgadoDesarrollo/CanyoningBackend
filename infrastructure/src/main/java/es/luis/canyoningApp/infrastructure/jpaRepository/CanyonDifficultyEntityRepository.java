package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.CanyonDifficultyEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

public interface CanyonDifficultyEntityRepository
    extends CrudRepository<CanyonDifficultyEntity, CanyonDifficultyEntity.PrimaryKeys> {
  @Modifying(clearAutomatically = true)
  void deleteByCanyonId(Long canyonId);
}
