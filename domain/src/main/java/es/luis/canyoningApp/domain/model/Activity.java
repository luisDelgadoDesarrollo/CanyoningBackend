package es.luis.canyoningApp.domain.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Activity {

  private Long activityId;

  private User user;

  private LocalDate date;

  private String meetingPlace;

  private String activityType;

  private Integer capacity;

  private String meetingTime;

  private String description;

  private String qr;

  private List<Long> participants = new ArrayList<>();

  private OffsetDateTime deleteAt;
}
