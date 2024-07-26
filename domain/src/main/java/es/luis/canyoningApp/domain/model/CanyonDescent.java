package es.luis.canyoningApp.domain.model;

import es.luis.canyoningApp.domain.Annotations.DocField;
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

    @DocField
    private Integer descentNumber;

    @DocField
    private String length;

    @DocField
    private String slope;

    @DocField
    private String rapelNum;

    @DocField
    private String maxLength;

    @DocField(isString = true)
    private String equipment;
}
