package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.ActivityEntity;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ActivityEntityRepository
    extends CrudRepository<ActivityEntity, Long>, PagingAndSortingRepository<ActivityEntity, Long> {

  @Query(
      "SELECT ae FROM ActivityEntity ae WHERE (ae.date = :date or :date is null) and (LOWER(ae.meetingPlace) like LOWER(:meetingPlace) or :meetingPlace is null)")
  Page<ActivityEntity> getActivities(LocalDate date, String meetingPlace, Pageable pageable);
}
