package es.luis.canyoningApp.domain.model;

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

    private String link;

    private String title;
}
