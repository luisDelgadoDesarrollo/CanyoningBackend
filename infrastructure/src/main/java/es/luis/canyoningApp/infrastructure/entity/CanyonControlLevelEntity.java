package es.luis.canyoningApp.infrastructure.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "canyoncontrollevel")
@IdClass(CanyonControlLevelEntity.PrimaryKeys.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CanyonControlLevelEntity {

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class PrimaryKeys implements Serializable {
    private Long canyonId;
    private String caudalLevel;
    private String controlPoint;
  }

  @Id
  @Column(name = "canyonId", nullable = false)
  private Long canyonId;

  @Id
  @Column(name = "caudalLevel", nullable = false)
  private String caudalLevel;

  @Column(name = "name", nullable = false)
  private String name;

  @Id
  @Column(name = "controlPoint", nullable = false)
  private String controlPoint;

  @ManyToOne
  @JoinColumn(name = "canyonId", nullable = false, insertable = false, updatable = false)
  private SimpleCanyonEntity canyon;
}
