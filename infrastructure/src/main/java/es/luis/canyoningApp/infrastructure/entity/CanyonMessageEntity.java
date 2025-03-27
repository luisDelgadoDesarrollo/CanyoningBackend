package es.luis.canyoningApp.infrastructure.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "canyonmessages")
public class CanyonMessageEntity {

  @Id
  @Column(name = "canyonMessageId")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long canyonMessageId;

  @ManyToOne
  @JoinColumn(name = "canyonId", nullable = false)
  private SimpleCanyonEntity canyon;

  @ManyToOne
  @JoinColumn(name = "userId", nullable = false)
  private UserEntity user;

  @Column(name = "message")
  private String message;

  @Column(name = "flow")
  private Integer flow;

  @Column(name = "temperature")
  private Integer temperature;

  @Column(name = "sendAt")
  private OffsetDateTime sendAt;
}
