package es.luis.canyoningApp.infrastructure.entity.PrimaryKey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class PrimaryKeyTokenUserId {

  @Column(name = "userId", nullable = false, updatable = false)
  private Long userId;

  @Column(name = "token", nullable = false)
  private String token;
}
