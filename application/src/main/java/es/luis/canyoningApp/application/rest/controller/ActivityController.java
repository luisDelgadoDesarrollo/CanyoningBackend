package es.luis.canyoningApp.application.rest.controller;

import es.luis.canyoningApp.application.rest.mapper.ActivityControllerMapper;
import es.luis.canyoningApp.canyoningApp.application.rest.api.ActivityApi;
import es.luis.canyoningApp.canyoningApp.application.rest.model.ActivityDto;
import es.luis.canyoningApp.canyoningApp.application.rest.model.SimpleActivityDto;
import es.luis.canyoningApp.domain.model.Activity;
import es.luis.canyoningApp.domain.service.ActivityService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class ActivityController extends BaseController implements ActivityApi {

  @Autowired private ActivityService activityService;

  @Autowired private ActivityControllerMapper activityControllerMapper;

  @Override
  public ResponseEntity<ActivityDto> createActivity(ActivityDto activityDto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            activityControllerMapper.activityToActivityDto(
                activityService.createActivity(
                    activityControllerMapper.activityDtoToActivity(activityDto))));
  }

  @Override
  public ResponseEntity<Void> deleteActivity(Long activityId) {
    activityService.deleteActivity(activityId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Override
  public ResponseEntity<List<SimpleActivityDto>> getActivities(
      LocalDate date,
      String meetingPlace,
      Integer page,
      Integer size,
      String sort,
      Pageable pageable) {
    Page<Activity> activities = activityService.getActivities(date, meetingPlace, pageable);
    addPaginationHeadersToResponse(activities);
    return ResponseEntity.ok(
        activities.stream().map(activityControllerMapper::activityToSimpleActivityDto).toList());
  }

  @Override
  public ResponseEntity<ActivityDto> getActivity(Long activityId) {
    return ResponseEntity.ok(
        activityControllerMapper.activityToActivityDto(activityService.getActivity(activityId)));
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
    return ResponseEntity.ok(
        activityControllerMapper.activityToActivityDto(
            activityService.updateActivity(
                activityId, activityControllerMapper.activityDtoToActivity(activityDto))));
  }
}
