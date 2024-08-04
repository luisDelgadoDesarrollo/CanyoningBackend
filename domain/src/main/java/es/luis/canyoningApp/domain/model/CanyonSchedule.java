package es.luis.canyoningApp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CanyonSchedule {
  private Long canyonId;

  private Integer car;

  private Integer descentNumber;

  private String approach;

  private String descent;

  private String _return;
}
