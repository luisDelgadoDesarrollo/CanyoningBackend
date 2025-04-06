package es.luis.canyoningApp.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CanyonLog {
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("canyon_id")
    private Long canyonId;

    @JsonProperty("date_search")
    private LocalDate dateSearch;
}
