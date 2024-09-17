package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.CanyonDifficultyEntity;
import org.springframework.data.repository.CrudRepository;

public interface CanyonDifficultyEntityRepository
    extends CrudRepository<CanyonDifficultyEntity, CanyonDifficultyEntity.PrimaryKeys> {
  void deleteByCanyonId(Long canyonId);
}
