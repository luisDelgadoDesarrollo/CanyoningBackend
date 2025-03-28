package es.luis.canyoningApp.infrastructure.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import lombok.Data;
import org.hibernate.annotations.Where;

@Data
@Entity
@Table(name = "canyon")
@Where(clause = "deleteAt IS NULL")
public class SimpleCanyonEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "canyonId", nullable = false, updatable = false)
  private Long canyonId;

  @Column(name = "name")
  private String name;

  @Column(name = "season")
  private String season;

  @Column(name = "description")
  private String description;

  @Column(name = "croquis")
  private String croquis;

  @Column(name = "river")
  private String river;

  @Column(name = "access")
  private String access;

  @Column(name = "approach")
  private String approach;

  @Column(name = "descent")
  private String descent;

  @Column(name = "_return")
  private String _return;

  @Column(name = "scape")
  private String scape;

  @Column(name = "deleteAt")
  private OffsetDateTime deleteAt;
}
