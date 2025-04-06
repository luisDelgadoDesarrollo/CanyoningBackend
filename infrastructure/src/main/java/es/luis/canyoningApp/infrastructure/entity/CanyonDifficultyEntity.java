package es.luis.canyoningApp.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Table(name = "canyondifficulty")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CanyonDifficultyEntity.PrimaryKeys.class)
public class CanyonDifficultyEntity {
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

    @Column(name = "difficultyDesc")
    private String difficultyDesc;
}
