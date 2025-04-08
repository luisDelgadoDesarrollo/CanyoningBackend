package es.luis.canyoningApp.infrastructure.entity.PrimaryKey;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ActivityUserId implements Serializable {

  private Long activityId;
  private Long userId;
}
