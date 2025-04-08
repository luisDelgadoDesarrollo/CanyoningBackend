package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.DownloadsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DownloadsEntityRepository
    extends JpaRepository<DownloadsEntity, DownloadsEntity.PrimaryKeys> {}
