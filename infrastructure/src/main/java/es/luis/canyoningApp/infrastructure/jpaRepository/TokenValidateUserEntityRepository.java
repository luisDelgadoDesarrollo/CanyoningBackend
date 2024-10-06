package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.PrimaryKey.PrimaryKeyTokenUserId;
import es.luis.canyoningApp.infrastructure.entity.TokenValidateUserEntity;
import java.time.OffsetDateTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TokenValidateUserEntityRepository
    extends CrudRepository<TokenValidateUserEntity, PrimaryKeyTokenUserId> {

  @Query(
      "SELECT tvue FROM TokenValidateUserEntity tvue WHERE tvue.token = :token AND tvue.deathTime > :now AND (tvue.used = false or tvue.used is null)")
  TokenValidateUserEntity findByToken(String token, OffsetDateTime now);
}
