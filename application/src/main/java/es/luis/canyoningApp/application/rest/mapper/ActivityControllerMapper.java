package es.luis.canyoningApp.application.rest.mapper;

import es.luis.canyoningApp.application.rest.model.ActivityDto;
import es.luis.canyoningApp.application.rest.model.SimpleActivityDto;
import es.luis.canyoningApp.domain.model.Activity;
import org.mapstruct.Mapper;

@Mapper
public interface ActivityControllerMapper {
  Activity activityDtoToActivity(ActivityDto activityDto);

  ActivityDto activityToActivityDto(Activity activity);

  SimpleActivityDto activityToSimpleActivityDto(Activity activity);
}
