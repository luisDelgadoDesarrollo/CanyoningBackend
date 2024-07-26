package es.luis.canyoningApp.domain.repository;

import es.luis.canyoningApp.domain.model.Activity;
import es.luis.canyoningApp.domain.model.User;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivityRepository {
  Activity createActivity(Activity activity);

  void insertActivityUser(Long activityId, User user);

  Activity getActivity(Long activityId);

  void deleteActivityUser(Long activityId);

  Page<Activity> getActivities(LocalDate date, String meetingPlace, Pageable pageable);

  void cancelActivity(Activity activity);
}
