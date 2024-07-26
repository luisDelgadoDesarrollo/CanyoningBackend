package es.luis.canyoningApp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CanyonDifficulty {

  private Long canyonId;

  private Integer descentNumber;

  private String difficultyDesc;
}
