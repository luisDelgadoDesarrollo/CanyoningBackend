package es.luis.canyoningApp.domain.model;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Token {

  private Long userId;
  private String token;
  private OffsetDateTime deathTime;
  private Boolean used;
}
