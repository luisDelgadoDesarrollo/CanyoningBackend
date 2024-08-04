package es.luis.canyoningApp.domain.service;

import es.luis.canyoningApp.domain.exception.ForbiddenException;
import es.luis.canyoningApp.domain.model.Activity;
import es.luis.canyoningApp.domain.repository.ActivityRepository;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import javax.xml.transform.TransformerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl extends BaseService implements ActivityService {

  @Autowired ActivityRepository activityRepository;

  @Autowired private UserService userService;

  @Override
  public Activity createActivity(Activity activity) {
    // todo generar qr
    activity.setQr("generar qr");
    boolean userInclude =
        activity.getParticipants().stream()
            .anyMatch(participante -> participante.equals(getAuthenticatedUserId()));
    if (!userInclude) {
      activity.getParticipants().add(getAuthenticatedUserId());
    }
    Activity activitySaved = activityRepository.createActivity(activity);
    activityRepository.deleteActivityUser(activitySaved.getActivityId());
    activity
        .getParticipants()
        .forEach(
            participante ->
                activityRepository.insertActivityUser(
                    activitySaved.getActivityId(), userService.getUser(participante)));

    return activitySaved;
  }

  @Override
  public Activity updateActivity(Long activityId, Activity activity) {
    Activity activitySaved = getActivity(activityId);
    Long authenticatedUserId = getAuthenticatedUserId();
    // todo enviar email a los participantes
    if (activitySaved.getUser().getUserId().equals(authenticatedUserId)) {
      activitySaved.setDate(activity.getDate());
      activitySaved.setMeetingPlace(activity.getMeetingPlace());
      activitySaved.setActivityType(activity.getActivityType());
      activitySaved.setCapacity(activity.getCapacity());
      activitySaved.setMeetingTime(activity.getMeetingTime());
      activitySaved.setDescription(activity.getDescription());
      activitySaved.setParticipants(activity.getParticipants());
      return createActivity(activitySaved);
    }
    throw new ForbiddenException(
        "No puedes actualizar esta actividad",
        new TransformerException(
            "No puedes actividad esta revio por que no eres el autor de ella"));
  }

  @Override
  public Activity getActivity(Long activityId) {
    return activityRepository.getActivity(activityId);
  }

  @Override
  public Page<Activity> getActivities(LocalDate date, String meetingPlace, Pageable pageable) {
    return activityRepository.getActivities(date, meetingPlace, pageable);
  }

  @Override
  public void deleteActivity(Long activityId) {
    // todo enviar email a los participantes
    Activity activity = getActivity(activityId);
    if (activity.getUser().getUserId().equals(getAuthenticatedUserId())) {
      activity.setDeleteAt(OffsetDateTime.now());
      activityRepository.cancelActivity(activity);
      return;
    }
  }
}
