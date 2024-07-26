package es.luis.canyoningApp.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "canyonprohibition")
public class CanyonProhibitionEntity {

    @Id
    @Column(name = "canyonProhibitionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long canyonProhibitionId;

    @ManyToOne
    @JoinColumn(name = "canyonId", nullable = false)
    private SimpleCanyonEntity canyon;

    @Column(name = "description")
    private String description;
}
