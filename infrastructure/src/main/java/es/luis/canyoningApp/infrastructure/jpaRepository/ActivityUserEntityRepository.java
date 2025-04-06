package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.ActivityUserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ActivityUserEntityRepository extends CrudRepository<ActivityUserEntity, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM ActivityUserEntity aue WHERE aue.id.activityId = :activityId")
    void removeAllByAcivityId(Long activityId);
}
