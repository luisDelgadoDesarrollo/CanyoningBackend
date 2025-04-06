package es.luis.canyoningApp.infrastructure.entity;

import es.luis.canyoningApp.infrastructure.entity.PrimaryKey.ActivityUserId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "activityuser")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityUserEntity {

    @EmbeddedId
    private ActivityUserId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;
}
