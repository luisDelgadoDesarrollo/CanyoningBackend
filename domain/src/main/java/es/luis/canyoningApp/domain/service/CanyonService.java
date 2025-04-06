package es.luis.canyoningApp.domain.service;

import es.luis.canyoningApp.domain.model.Canyon;
import es.luis.canyoningApp.domain.model.LocationCanyon;
import es.luis.canyoningApp.domain.model.SimpleCanyon;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CanyonService {
    Canyon createCanyon(Canyon canyon);

    Canyon getCanyonById(Long canyonId);

    Page<SimpleCanyon> getCanyons(
            String name,
            String season,
            String river,
            String country,
            String population,
            Pageable pageable);

    void deleteCanyon(Long canyonId);

    Canyon updateCanyon(Long canyonId, Canyon canyon);

    Resource downloadCanyon(Long canyonId, Boolean email);

    List<LocationCanyon> getLocations();

    Page<SimpleCanyon> getFavouriteCanyons(
            String name,
            String season,
            String river,
            String country,
            String population,
            Pageable pageable);

    void addCanyonToFavourites(Long canyonId);

    void deleteCanyonFromFavourites(Long canyonId);
}
