package es.luis.canyoningApp.application.rest.controller;

import es.luis.canyoningApp.canyoningApp_application.rest.api.ActivityApi;
import es.luis.canyoningApp.canyoningApp_application.rest.model.ActivityDto;
import es.luis.canyoningApp.canyoningApp_application.rest.model.SimpleActivityDto;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class ActivityController implements ActivityApi {
  @Override
  public ResponseEntity<ActivityDto> createActivity(ActivityDto activityDto) {
    return null;
  }

  @Override
  public ResponseEntity<Void> deleteActivity(Long activityId) {
    return null;
  }

  @Override
  public ResponseEntity<List<SimpleActivityDto>> getActivities(String name) {
    return null;
  }

  @Override
  public ResponseEntity<List<ActivityDto>> getActivity(Long activityId) {
    return null;
  }

  @Override
  public ResponseEntity<Void> insertActivity(Long activityId, Long userId) {
    return null;
  }

  @Override
  public ResponseEntity<Void> shareActivity(Long activityId) {
    return null;
  }

  @Override
  public ResponseEntity<ActivityDto> updateActivity(Long activityId, ActivityDto activityDto) {
    return null;
  }
}
