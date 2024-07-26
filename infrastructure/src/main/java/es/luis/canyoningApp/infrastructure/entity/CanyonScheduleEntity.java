package es.luis.canyoningApp.infrastructure.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "canyonschedule")
@IdClass(CanyonScheduleEntity.PrimaryKeys.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CanyonScheduleEntity {

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class PrimaryKeys implements Serializable {
    private Long canyonId;
    private Integer car;
    private Integer descentNumber;
  }

  @Id
  @Column(name = "canyonId", nullable = false)
  private Long canyonId;

  @Id
  @Column(name = "car", nullable = false)
  private Integer car;

  @Id
  @Column(name = "descentNumber", nullable = false)
  private Integer descentNumber;

  @ManyToOne
  @JoinColumn(name = "canyonId", nullable = false, insertable = false, updatable = false)
  private SimpleCanyonEntity canyon;

  @Column(name = "approach")
  private String approach;

  @Column(name = "descent")
  private String descent;

  @Column(name = "_return")
  private String _return;
}
