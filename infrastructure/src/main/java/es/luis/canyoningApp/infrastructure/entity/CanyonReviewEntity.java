package es.luis.canyoningApp.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "canyonreview")
@Where(clause = "deleteAt IS NULL")
public class CanyonReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "canyonReviewId", nullable = false, updatable = false)
    private Long canyonReviewId;

    @ManyToOne
    @JoinColumn(name = "canyonId", nullable = false)
    private SimpleCanyonEntity canyon;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "combinedCar")
    private Boolean combinedCar;

    @Column(name = "description")
    private String description;

    @Column(name = "qr")
    private String qr;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "caudal")
    private Integer caudal;

    @Column(name = "deleteAt")
    private OffsetDateTime deleteAt;
}
