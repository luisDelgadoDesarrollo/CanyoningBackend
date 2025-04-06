package es.luis.canyoningApp.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "canyondescent")
@IdClass(CanyonDescentEntity.PrimaryKeys.class)
public class CanyonDescentEntity {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PrimaryKeys implements Serializable {
        private Long canyonId;
        private Integer descentNumber;
    }

    @Id
    @Column(name = "canyonId", nullable = false)
    private Long canyonId;

    @Id
    @Column(name = "descentNumber", nullable = false)
    private Integer descentNumber;

    @ManyToOne
    @JoinColumn(name = "canyonId", nullable = false, insertable = false, updatable = false)
    private SimpleCanyonEntity canyon;

    @Column(name = "length")
    private String length;

    @Column(name = "slope")
    private String slope;

    @Column(name = "rapelNum")
    private String rapelNum;

    @Column(name = "maxLength")
    private String maxLength;

    @Column(name = "equipment")
    private String equipment;
}
