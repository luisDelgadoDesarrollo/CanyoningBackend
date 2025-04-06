package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.CanyonLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CanyonLocationEntityRepository extends JpaRepository<CanyonLocationEntity, Long> {
}
