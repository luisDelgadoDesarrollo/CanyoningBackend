package es.luis.canyoningApp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CanyonReview {

    // In the future there will be fields like simpleClimb to colimb reviews

    private Long canyonReviewId;

    private SimpleCanyon canyon;

    private User user;

    private LocalDate date;

    private Integer duration;

    private Boolean combinedCar;

    private String description;

    private String qr;

    private Integer caudal;

    private List<User> users = new ArrayList<>();

    private OffsetDateTime deleteAt;
}
