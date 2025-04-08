package es.luis.canyoningApp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CanyonControlLevel {
  private Long canyonId;
  private String caudalLevel;
  private String name;
  private String controlPoint;
}
