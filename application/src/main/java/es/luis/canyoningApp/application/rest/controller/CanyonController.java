package es.luis.canyoningApp.application.rest.controller;

import es.luis.canyoningApp.canyoningApp_application.rest.api.CanyonApi;
import es.luis.canyoningApp.canyoningApp_application.rest.model.CanyonDto;
import es.luis.canyoningApp.canyoningApp_application.rest.model.SimpleCanyonDto;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class CanyonController implements CanyonApi {
  @Override
  public ResponseEntity<CanyonDto> createCanyon(CanyonDto canyonDto) {
    return null;
  }

  @Override
  public ResponseEntity<Void> deleteCanyon(Long canyonId) {
    return null;
  }

  @Override
  public ResponseEntity<Void> downloadCanyon(Long canyonId) {
    return null;
  }

  @Override
  public ResponseEntity<List<CanyonDto>> getCanyon(Long canyonId) {
    return null;
  }

  @Override
  public ResponseEntity<List<SimpleCanyonDto>> getCanyons(String name) {
    return null;
  }

  @Override
  public ResponseEntity<CanyonDto> updateCanyon(Long canyonId, CanyonDto canyonDto) {
    return null;
  }
}
