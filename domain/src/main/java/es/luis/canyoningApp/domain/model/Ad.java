package es.luis.canyoningApp.domain.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ad {

  private Long adId;

  private String name;

  private String link;

  private LocalDate startDate;

  private LocalDate endDate;

  private Integer amount;

  private Boolean paid;
}
