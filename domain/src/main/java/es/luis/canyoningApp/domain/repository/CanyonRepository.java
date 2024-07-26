package es.luis.canyoningApp.domain.repository;

import es.luis.canyoningApp.domain.model.Canyon;
import es.luis.canyoningApp.domain.model.SimpleCanyon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CanyonRepository {
  Canyon getCanyonById(Long canyonId);

  Page<SimpleCanyon> getCanyons(
      String name, String season, String river, String population, Pageable pageable);

  void deleteCanyon(SimpleCanyon simpleCanyon);

  Canyon createCanyon(Canyon canyon);

  Canyon updateCanyon(Canyon canyon);
}
