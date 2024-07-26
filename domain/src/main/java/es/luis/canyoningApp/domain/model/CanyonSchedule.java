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
public class CanyonSchedule {
    private Long canyonId;

    @DocField
    private Integer car;

    @DocField
    private Integer descentNumber;

    @DocField
    private String approach;

    @DocField
    private String descent;

    @DocField
    private String _return;
}
