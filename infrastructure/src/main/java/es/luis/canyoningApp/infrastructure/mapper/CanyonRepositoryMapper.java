package es.luis.canyoningApp.infrastructure.mapper;

import es.luis.canyoningApp.domain.model.*;
import es.luis.canyoningApp.infrastructure.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CanyonRepositoryMapper {

  Canyon canyonEntityToCanyon(CanyonEntity canyonEntity);

  SimpleCanyon canyonEntityToSimpleCanyon(CanyonEntity canyonEntity);

  CanyonRappeling canyonRappelingEntityToCanyonRappeling(
      CanyonRappelingEntity canyonRappelingEntity);

  CanyonRappelingEntity canyonRappelingToCanyonRappelingEntity(CanyonRappeling canyonRappeling);

  CanyonDescent canyonDescentEntityToCanyonDescent(CanyonDescentEntity canyonDescentEntity);

  CanyonDescentEntity canyonDescentToCanyonDescentEntity(CanyonDescent canyonDescent);

  CanyonSchedule canyonScheduleEntityToCanyonSchedule(CanyonScheduleEntity canyonScheduleEntity);

  CanyonScheduleEntity canyonScheduleToCanyonScheduleEntity(CanyonSchedule canyonSchedule);

  CanyonDifficulty canyonDifficultyEntityToCanyonDifficulty(
      CanyonDifficultyEntity canyonDifficultyEntity);

  CanyonDifficultyEntity canyonDifficultyToCanyonDifficultyEntity(
      CanyonDifficulty canyonDifficulty);

  @Mapping(target = "canyonId", source = "canyon.canyonId")
  CanyonProhibition canyonProhibitionEntityToCanyonProhibition(
      CanyonProhibitionEntity canyonProhibitionEntity);

  CanyonProhibitionEntity canyonProhibitionEntityToCanyonProhibition(
      CanyonProhibition canyonProhibition);

  @Mapping(target = "canyonId", source = "canyon.canyonId")
  CanyonLink canyonLinkEntityToCanyonLink(CanyonLinkEntity canyonLinkEntity);

  CanyonLinkEntity canyonLinkToCanyonLinkEntity(CanyonLink canyonLink);

  CanyonCanyonNear canyonCanyonNearEntityToCanyonCanyonNear(
      CanyonCanyonNearEntity canyonCanyonNearEntity);

  CanyonCanyonNearEntity canyonCanyonNearToCanyonCanyonNearEntity(
      CanyonCanyonNear canyonCanyonNear);

  SimpleCanyonEntity simpleCanyonToSimpleCanyonEntity(SimpleCanyon simpleCanyon);

  // todo review this, its weird
  @Mapping(target = "location.longitud", source = "location.longitud")
  @Mapping(target = "location.latitud", source = "location.latitud")
  CanyonEntity canyonToCanyonEntity(Canyon canyon);

  SimpleCanyonEntity canyonEntityToSimpleCanyonEntity(CanyonEntity canyonEntity);

  @Mapping(target = "longitud", source = "location.longitud")
  @Mapping(target = "latitud", source = "location.latitud")
  LocationCanyon canyonEntityToLocationCanyon(CanyonEntity canyonEntity);
}
