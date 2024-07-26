package es.luis.canyoningApp.infrastructure.mapper;

import es.luis.canyoningApp.domain.model.SimpleCanyon;
import es.luis.canyoningApp.infrastructure.entity.CanyonEntity;
import es.luis.canyoningApp.infrastructure.entity.SimpleCanyonEntity;
import org.mapstruct.Mapper;

@Mapper
public interface SimpleCanyonRepositoryMapper {
  SimpleCanyon simpleCanyonEntityToSimpleCanyon(SimpleCanyonEntity simpleCanyonEntity);

  SimpleCanyonEntity simpleCanyonToSimpleCanyonEntity(SimpleCanyon simpleCanyon);

  CanyonEntity simpleCanyonEntityToCanyonEntity(SimpleCanyonEntity save);
}
