package es.luis.canyoningApp.application.rest.controller;

import es.luis.canyoningApp.application.rest.mapper.CanyonControllerMapper;
import es.luis.canyoningApp.canyoningApp_application.rest.api.CanyonApi;
import es.luis.canyoningApp.canyoningApp_application.rest.model.CanyonDto;
import es.luis.canyoningApp.canyoningApp_application.rest.model.LocationCanyonDto;
import es.luis.canyoningApp.canyoningApp_application.rest.model.SimpleCanyonDto;
import es.luis.canyoningApp.domain.model.SimpleCanyon;
import es.luis.canyoningApp.domain.service.CanyonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class CanyonController extends BaseController implements CanyonApi {

  @Autowired private CanyonService canyonService;
  @Autowired private CanyonControllerMapper canyonControllerMapper;

  @Override
  public ResponseEntity<CanyonDto> createCanyon(CanyonDto canyonDto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            canyonControllerMapper.canyonToCanyonDto(
                canyonService.createCanyon(canyonControllerMapper.canyonDtoToCanyon(canyonDto))));
  }

  @Override
  public ResponseEntity<Void> deleteCanyon(Long canyonId) {
    canyonService.deleteCanyon(canyonId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Override
  public ResponseEntity<Resource> downloadCanyon(Long canyonId, Boolean email) {
    return ResponseEntity.status(HttpStatus.OK).body(canyonService.downloadCanyon(canyonId, email));
  }

  @Override
  public ResponseEntity<CanyonDto> getCanyon(Long canyonId) {
    return ResponseEntity.ok(
        canyonControllerMapper.canyonToCanyonDto(canyonService.getCanyonById(canyonId)));
  }

  @Override
  public ResponseEntity<List<SimpleCanyonDto>> getCanyons(
      String name,
      String season,
      String river,
      String location,
      String population,
      Integer page,
      Integer size,
      String sort,
      Pageable pageable) {
    Page<SimpleCanyon> canyons =
        canyonService.getCanyons(name, season, river, population, pageable);
    addPaginationHeadersToResponse(canyons);
    return ResponseEntity.ok(
        canyons.stream().map(canyonControllerMapper::simpleCanyonToSimpleCanyon).toList());
  }

  @Override
  public ResponseEntity<List<LocationCanyonDto>> getLocations() {
    return ResponseEntity.ok(
        canyonService.getLocations().stream()
            .map(canyonControllerMapper::locationCanyonToLocationCanyonDto)
            .toList());
  }

  @Override
  public ResponseEntity<CanyonDto> updateCanyon(Long canyonId, CanyonDto canyonDto) {
    return ResponseEntity.ok(
        canyonControllerMapper.canyonToCanyonDto(
            canyonService.updateCanyon(
                canyonId, canyonControllerMapper.canyonDtoToCanyon(canyonDto))));
  }
}
