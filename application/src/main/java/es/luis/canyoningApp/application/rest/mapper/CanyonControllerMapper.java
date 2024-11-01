package es.luis.canyoningApp.application.rest.mapper;

import es.luis.canyoningApp.canyoningApp_application.rest.model.CanyonDto;
import es.luis.canyoningApp.canyoningApp_application.rest.model.LocationCanyonDto;
import es.luis.canyoningApp.canyoningApp_application.rest.model.SimpleCanyonDto;
import es.luis.canyoningApp.domain.model.Canyon;
import es.luis.canyoningApp.domain.model.LocationCanyon;
import es.luis.canyoningApp.domain.model.SimpleCanyon;
import org.mapstruct.Mapper;

@Mapper
public interface CanyonControllerMapper {

  Canyon canyonDtoToCanyon(CanyonDto canyonDto);

  CanyonDto canyonToCanyonDto(Canyon canyon);

  SimpleCanyonDto simpleCanyonToSimpleCanyon(SimpleCanyon canyons);

  LocationCanyonDto locationCanyonToLocationCanyonDto(LocationCanyon locationCanyon);
}
