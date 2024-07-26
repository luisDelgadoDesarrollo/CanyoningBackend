package es.luis.canyoningApp.infrastructure.entity;

import es.luis.canyoningApp.infrastructure.entity.PrimaryKey.ReviewUserId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "canyonreviewusers")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CanyonReviewUserEntity {

  @EmbeddedId private ReviewUserId id;

  @ManyToOne
  @MapsId("userId")
  @JoinColumn(name = "userId", nullable = false)
  private UserEntity user;
}
