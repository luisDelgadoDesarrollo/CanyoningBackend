package es.luis.canyoningApp.infrastructure.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import lombok.Data;
import org.hibernate.annotations.Where;

@Data
@Entity
@Table(name = "user")
@Where(clause = "deleteAt IS NULL")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "userId", nullable = false, updatable = false)
  private Long userId;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "lastName", nullable = false)
  private String lastName;

  @Column(name = "birthDay", nullable = false)
  private LocalDate birthDay;

  @Column(name = "location", nullable = false)
  private String location;

  @Column(name = "description", nullable = true)
  private String description;

  @Column(name = "plan", nullable = true)
  private Integer plan;

  @Column(name = "guia", nullable = true)
  private boolean guia;

  @Column(name = "validated", nullable = false)
  private boolean validated;

  @Column(name = "deleteAt", nullable = true)
  private OffsetDateTime deleteAt;
}
