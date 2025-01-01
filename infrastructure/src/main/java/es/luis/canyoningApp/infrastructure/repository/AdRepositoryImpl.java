package es.luis.canyoningApp.infrastructure.repository;

import es.luis.canyoningApp.domain.model.Ad;
import es.luis.canyoningApp.domain.repository.AdRepository;
import es.luis.canyoningApp.infrastructure.jpaRepository.AdEntityRepository;
import es.luis.canyoningApp.infrastructure.mapper.AdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class AdRepositoryImpl implements AdRepository {

    @Autowired
    private AdEntityRepository adEntityRepository;

    @Autowired
    private AdMapper adMapper;

    @Override
    public Ad getAdByDate(LocalDate now) {
        return adEntityRepository.findByDate(now).map(adMapper::adEntityToAd).orElse(null);
    }
}
