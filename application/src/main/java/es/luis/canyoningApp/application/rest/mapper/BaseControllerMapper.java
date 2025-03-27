package es.luis.canyoningApp.application.rest.mapper;

import es.luis.canyoningApp.application.rest.model.ActivityTypeDto;
import es.luis.canyoningApp.domain.model.ActivityType;

public interface BaseControllerMapper {

  ActivityType activityTypeDtoToActivityType(ActivityTypeDto activityTypeDto);
}
