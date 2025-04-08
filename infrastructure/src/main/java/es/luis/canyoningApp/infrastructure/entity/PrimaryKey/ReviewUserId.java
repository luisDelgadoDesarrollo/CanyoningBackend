package es.luis.canyoningApp.infrastructure.entity.PrimaryKey;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ReviewUserId {
  private Long canyonReviewId;
  private Long userId;
}
