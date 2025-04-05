package es.luis.canyoningApp.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "favouritescanyon")
@IdClass(FavouritesCanyonEntity.PrimaryKeys.class)
public class FavouritesCanyonEntity {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PrimaryKeys implements Serializable {
        private Long userId;
        private Long canyonId;
    }

    @JoinColumn(name = "userId", insertable = false, updatable = false)
    @ManyToOne
    private UserEntity user;

    @Id
    @Column(name = "userId")
    private Long userId;

    @JoinColumn(name = "canyonId", insertable = false, updatable = false)
    @ManyToOne
    private CanyonEntity canyon;

    @Id
    @Column(name = "canyonId")
    private Long canyonId;
}
