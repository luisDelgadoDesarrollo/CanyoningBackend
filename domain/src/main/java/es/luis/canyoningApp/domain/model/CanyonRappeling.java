package es.luis.canyoningApp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CanyonRappeling {

    private Long canyonId;

    private Integer descentNumber;

    private String step;

    private String stepType;

    private String length;

    private String location;

    private String description;
}
