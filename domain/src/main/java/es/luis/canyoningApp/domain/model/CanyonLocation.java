package es.luis.canyoningApp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CanyonLocation {
  private Long canyonId;

  private String population;

  private String latitud;

  private String longitud;

  private String zone;

  private String country;
}
