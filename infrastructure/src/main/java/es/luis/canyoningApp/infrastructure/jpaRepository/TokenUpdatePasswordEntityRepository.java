package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.PrimaryKey.PrimaryKeyTokenUserId;
import es.luis.canyoningApp.infrastructure.entity.TokenUpdatePasswordEntity;
import java.time.OffsetDateTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenUpdatePasswordEntityRepository
    extends CrudRepository<TokenUpdatePasswordEntity, PrimaryKeyTokenUserId> {

  @Query(
      "SELECT COUNT(tupe) FROM TokenUpdatePasswordEntity tupe WHERE tupe.userId = :userId AND tupe.token = :token AND tupe.deathTime > :now AND (tupe.used = false or tupe.used is null)")
  Long validatePasswordResetToken(Long userId, String token, OffsetDateTime now);
}
