package es.luis.canyoningApp.infrastructure.mapper;

import es.luis.canyoningApp.domain.model.Activity;
import es.luis.canyoningApp.infrastructure.entity.ActivityEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ActivityRepositoryMapper {
  ActivityEntity activityToActivityEntity(Activity activity);

  Activity activityEntityToActivity(ActivityEntity save);
}
