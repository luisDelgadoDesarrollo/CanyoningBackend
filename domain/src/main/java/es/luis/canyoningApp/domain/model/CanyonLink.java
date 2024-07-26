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
public class CanyonLink {

    private Long canyonLinkId;

    private Long canyonId;

    @DocField
    private String link;

    @DocField
    private String title;
}
