package es.luis.canyoningApp.infrastructure.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "activity")
public class ActivityEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "activityId", nullable = false, updatable = false)
  private Long activityId;

  @ManyToOne
  @JoinColumn(name = "userId", nullable = false)
  private UserEntity user;

  @Column(name = "date", nullable = false)
  private LocalDate date;

  @Column(name = "capacity")
  private Integer capacity;

  @Column(name = "activityType")
  private String activityType;

  @Column(name = "meetingPlace")
  private String meetingPlace;

  @Column(name = "meetingTime")
  private String meetingTime;

  @Column(name = "qr")
  private String qr;

  @Column(name = "description")
  private String description;

  @Column(name = "deleteAt")
  private OffsetDateTime deleteAt;
}
