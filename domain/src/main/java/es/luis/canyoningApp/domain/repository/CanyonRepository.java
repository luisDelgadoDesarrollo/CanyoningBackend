package es.luis.canyoningApp.domain.repository;

import es.luis.canyoningApp.domain.model.Canyon;
import es.luis.canyoningApp.domain.model.LocationCanyon;
import es.luis.canyoningApp.domain.model.SimpleCanyon;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CanyonRepository {
  Canyon getCanyonById(Long canyonId);

  Page<SimpleCanyon> getCanyons(
      String name,
      String season,
      String river,
      String country,
      String population,
      Pageable pageable);

  void deleteCanyon(SimpleCanyon simpleCanyon);

  Canyon createCanyon(Canyon canyon);

  Canyon updateCanyon(Canyon canyon);

  List<LocationCanyon> getLocations();

  Page<SimpleCanyon> getFavouritesCanyons(
      Long userId,
      String name,
      String season,
      String river,
      String country,
      String population,
      Pageable pageable);

  void deleteCanyonFromFavourites(Long userId, Long canyonId);

  void addCanyonToFavourites(Long userId, Long canyonId);

  Boolean isCanyonFavourite(Long canyonId, Long authenticatedUserId);
}
