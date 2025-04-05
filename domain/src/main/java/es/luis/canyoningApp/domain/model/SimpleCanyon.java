package es.luis.canyoningApp.domain.model;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleCanyon {

  private Long canyonId;

  private String name;

  private String croquis;

  private String description;

  private String season;

  private String river;

  private CanyonLocation location;

  private String access;

  private String approach;

  private String descent;

  private String _return;

  private String scape;

  private Boolean favourite;

  private OffsetDateTime deleteAt;
}
