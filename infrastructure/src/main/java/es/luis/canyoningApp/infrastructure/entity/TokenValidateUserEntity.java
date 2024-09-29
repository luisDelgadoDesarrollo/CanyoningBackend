package es.luis.canyoningApp.infrastructure.entity;

import es.luis.canyoningApp.infrastructure.entity.PrimaryKey.PrimaryKeyTokenUserId;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tokenvalidateuser")
@IdClass(PrimaryKeyTokenUserId.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenValidateUserEntity {

  @Id
  @Column(name = "userId", nullable = false, updatable = false)
  private Long userId;

  @Id
  @Column(name = "token", nullable = false)
  private String token;

  @Column(name = "deathTime", nullable = false, updatable = false)
  private OffsetDateTime deathTime;

  @Column(name = "used", nullable = false)
  private Boolean used;
}
