package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.CanyonDescentEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

public interface CanyonDescentEntityRepository
    extends CrudRepository<CanyonDescentEntity, CanyonDescentEntity.PrimaryKeys> {
  @Modifying(clearAutomatically = true)
  void deleteByCanyonId(Long canyonId);
}
