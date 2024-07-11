package es.luis.canyoningApp.domain.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.Period;
import lombok.experimental.SuperBuilder;

@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@SuperBuilder
@lombok.Data
public class User {

  private Long userId;

  private String password;

  private String email;

  private String name;

  private String lastName;

  private LocalDate birthDay;

  private String location;

  private String description;

  private Integer plan;

  private Boolean guia;

  private OffsetDateTime deleteAt;

  public Integer getAge() {
    if (this.birthDay == null) {
      return null;
    }
    LocalDate currentDate = LocalDate.now();
    if (this.birthDay.isAfter(currentDate)) {
      return null;
    }
    return Period.between(this.birthDay, currentDate).getYears();
  }
}
