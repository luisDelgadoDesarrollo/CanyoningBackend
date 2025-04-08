package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.CanyonReviewUserEntity;
import es.luis.canyoningApp.infrastructure.entity.UserEntity;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CanyonReviewUserEntityRepository
    extends CrudRepository<CanyonReviewUserEntity, Long> {

  @Transactional
  @Modifying
  @Query("DELETE FROM CanyonReviewUserEntity crue WHERE crue.id.canyonReviewId = :reviewId ")
  void removeAllByReviewId(Long reviewId);

  @Query(
      "SELECT crue.user FROM CanyonReviewUserEntity crue WHERE crue.id.canyonReviewId = :reviewId ")
  List<UserEntity> getUserByReviewId(Long reviewId);
}
