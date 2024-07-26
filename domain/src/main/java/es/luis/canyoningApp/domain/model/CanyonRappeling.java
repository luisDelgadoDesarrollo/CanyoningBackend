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
public class CanyonRappeling {

    private Long canyonId;

    @DocField
    private Integer descentNumber;

    @DocField
    private String step;

    @DocField
    private String stepType;

    @DocField
    private String length;

    @DocField
    private String location;

    @DocField(isString = true)
    private String description;
}
