package es.luis.canyoningApp.infrastructure.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "downloads")
@IdClass(DownloadsEntity.PrimaryKeys.class)
public class DownloadsEntity {

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class PrimaryKeys implements Serializable {
    private Long canyonId;
    private Long userId;
    private OffsetDateTime date;
  }

  @Id
  @Column(name = "canyonId", nullable = false)
  private Long canyonId;

  @Id
  @Column(name = "userId", nullable = false)
  private Long userId;

  @Id
  @Column(name = "downloadDate", nullable = false)
  private OffsetDateTime date;
}
