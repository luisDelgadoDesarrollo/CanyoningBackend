package es.luis.canyoningApp.domain.repository;

import java.time.OffsetDateTime;

public interface DownloadRepository {

    void saveDownload(Long canyonId, Long userId, OffsetDateTime date);
}
