package es.luis.canyoningApp.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "canyonlocation")
public class CanyonLocationEntity {

    @Id
    @Column(name = "canyonId")
    private Long canyonId;

    @Column(name = "poblacion")
    private String population;

    @Column(name = "latitud")
    private String latitud;

    @Column(name = "longitud")
    private String longitud;

    @Column(name = "zona")
    private String zone;

    @Column(name = "country")
    private String country;
}
