package es.luis.canyoningApp.domain.service;

import es.luis.canyoningApp.domain.model.Activity;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivityService {
  Activity createActivity(Activity activity);

  Activity updateActivity(Long activityId, Activity activity);

  Activity getActivity(Long activityId);

  Page<Activity> getActivities(LocalDate date, String meetingPlace, Pageable pageable);

  void deleteActivity(Long activityId);
}
