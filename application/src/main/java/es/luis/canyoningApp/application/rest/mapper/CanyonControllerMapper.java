package es.luis.canyoningApp.application.rest.mapper;

import es.luis.canyoningApp.application.rest.model.CanyonDto;
import es.luis.canyoningApp.application.rest.model.LocationCanyonDto;
import es.luis.canyoningApp.application.rest.model.SimpleCanyonDto;
import es.luis.canyoningApp.domain.model.Canyon;
import es.luis.canyoningApp.domain.model.LocationCanyon;
import es.luis.canyoningApp.domain.model.SimpleCanyon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CanyonControllerMapper {

  Canyon canyonDtoToCanyon(CanyonDto canyonDto);

  CanyonDto canyonToCanyonDto(Canyon canyon);

  @Mapping(target = "population", source = "location.population")
  @Mapping(target = "country", source = "location.country")
  SimpleCanyonDto simpleCanyonToSimpleCanyon(SimpleCanyon canyons);

  LocationCanyonDto locationCanyonToLocationCanyonDto(LocationCanyon locationCanyon);
}
