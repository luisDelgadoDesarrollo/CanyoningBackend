package es.luis.canyoningApp.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ads")
@AllArgsConstructor
@NoArgsConstructor
public class AdEntity {

  @Id
  @Column(name = "adId", nullable = false)
  private Long adId;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "link", nullable = true)
  private String link;

  @Column(name = "startDate", nullable = false)
  private LocalDate startDate;

  @Column(name = "endDate", nullable = false)
  private LocalDate endDate;

  @Column(name = "amount", nullable = false)
  private Integer amount;

  @Column(name = "paid", nullable = false)
  private Boolean paid;
}
