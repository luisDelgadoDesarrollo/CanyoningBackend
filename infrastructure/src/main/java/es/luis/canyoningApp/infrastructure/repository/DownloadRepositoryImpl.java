package es.luis.canyoningApp.infrastructure.repository;

import es.luis.canyoningApp.domain.repository.DownloadRepository;
import es.luis.canyoningApp.infrastructure.entity.DownloadsEntity;
import es.luis.canyoningApp.infrastructure.jpaRepository.DownloadsEntityRepository;
import java.time.OffsetDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DownloadRepositoryImpl implements DownloadRepository {
  @Autowired private DownloadsEntityRepository downloadsEntityRepository;

  @Override
  public void saveDownload(Long canyonId, Long userId, OffsetDateTime date) {
    downloadsEntityRepository.save(new DownloadsEntity(canyonId, userId, date));
  }
}
