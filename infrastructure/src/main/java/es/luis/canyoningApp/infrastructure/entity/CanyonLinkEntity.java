package es.luis.canyoningApp.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "canyonlink")
public class CanyonLinkEntity {

    @Id
    @Column(name = "canyonlinkId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long canyonLinkId;

    @ManyToOne
    @JoinColumn(name = "canyonId", nullable = false)
    private SimpleCanyonEntity canyon;

    @Column(name = "link")
    private String link;

    @Column(name = "title")
    private String title;
}
