package es.luis.canyoningApp.infrastructure.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "canyoncanyonnear")
@IdClass(CanyonCanyonNearEntity.PrimaryKeys.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CanyonCanyonNearEntity {

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class PrimaryKeys implements Serializable {
    private Long principalCanyon;
    private String nearCanyon;
  }

  @Id
  @Column(name = "canyonId", nullable = false)
  private Long principalCanyon;

  @Id
  @Column(name = "nearCanyon", nullable = false)
  private String nearCanyon;

  @ManyToOne
  @JoinColumn(name = "canyonId", nullable = false, insertable = false, updatable = false)
  private SimpleCanyonEntity canyon;
}
