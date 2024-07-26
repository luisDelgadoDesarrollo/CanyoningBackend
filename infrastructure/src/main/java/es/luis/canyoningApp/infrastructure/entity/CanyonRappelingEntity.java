package es.luis.canyoningApp.infrastructure.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "canyonrappeling")
@IdClass(CanyonRappelingEntity.PrimaryKeys.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CanyonRappelingEntity {

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class PrimaryKeys implements Serializable {
    private Long canyonId;
    private Integer descentNumber;
    private String step;
  }

  @Id
  @Column(name = "canyonId", nullable = false)
  private Long canyonId;

  @Id
  @Column(name = "descentNumber", nullable = false)
  private Integer descentNumber;

  @Id
  @Column(name = "step", nullable = false)
  private String step;

  @ManyToOne
  @JoinColumn(name = "canyonId", nullable = false, insertable = false, updatable = false)
  private SimpleCanyonEntity canyon;

  @Column(name = "stepType")
  private String stepType;

  @Column(name = "length")
  private String length;

  @Column(name = "location")
  private String location;

  @Column(name = "description")
  private String description;
}
