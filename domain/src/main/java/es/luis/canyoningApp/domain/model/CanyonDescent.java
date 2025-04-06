package es.luis.canyoningApp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CanyonDescent {
    private Long canyonId;

    private Integer descentNumber;

    private String length;

    private String slope;

    private String rapelNum;

    private String maxLength;

    private String equipment;
}
