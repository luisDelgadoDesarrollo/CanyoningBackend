package es.luis.canyoningApp.domain.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationCanyon {

  private Long canyonId;
  private String name;
  private String season;
  private String longitud;
  private String latitud;
  private List<CanyonDifficulty> canyonDifficulty;
}
