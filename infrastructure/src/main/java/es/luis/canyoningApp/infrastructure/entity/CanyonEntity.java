package es.luis.canyoningApp.infrastructure.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.Data;
import org.hibernate.annotations.Where;

@Data
@Entity
@Table(name = "canyon")
@Where(clause = "deleteAt IS NULL")
public class CanyonEntity {

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

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "canyonId", referencedColumnName = "canyonId")
  private CanyonLocationEntity location;

  @Column(name = "access")
  private String access;

  @Column(name = "approach")
  private String approach;

  @Column(name = "coverage")
  private String coverage;

  @Column(name = "descent")
  private String descent;

  @Column(name = "_return")
  private String _return;

  @Column(name = "scape")
  private String scape;

  @Column(name = "deleteAt")
  private OffsetDateTime deleteAt;

  @OneToMany(mappedBy = "canyon")
  private List<CanyonRappelingEntity> canyonRappeling;

  @OneToMany(mappedBy = "canyon")
  private List<CanyonDescentEntity> canyonDescent;

  @OneToMany(mappedBy = "canyon")
  private List<CanyonScheduleEntity> canyonSchedule;

  @OneToMany(mappedBy = "canyon")
  private List<CanyonLinkEntity> canyonLink;

  @OneToMany(mappedBy = "canyon")
  private List<CanyonProhibitionEntity> canyonProhibition;

  @OneToMany(mappedBy = "canyon")
  private List<CanyonDifficultyEntity> canyonDifficulty;

  @OneToMany(mappedBy = "canyon")
  private List<CanyonCanyonNearEntity> canyonCanyonNear;

  @OneToMany(mappedBy = "canyon")
  private List<CanyonControlLevelEntity> canyonControlLevel;
}
