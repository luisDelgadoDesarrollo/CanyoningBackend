package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.AdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface AdEntityRepository extends JpaRepository<AdEntity, Long> {
    @Query("SELECT ae from AdEntity ae WHERE :now <= ae.endDate AND now >= ae.startDate")
    Optional<AdEntity> findByDate(LocalDate now);
}
