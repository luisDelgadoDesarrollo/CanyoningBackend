package es.luis.canyoningApp.domain.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
  private Long messageId;

  private ActivityType typePlace;

  private Long placeId;

  private User user;

  private String message;

  private Integer flow;

  private Integer temperature;

  private LocalDate date;

  private OffsetDateTime sendAt;
}
