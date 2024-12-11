package es.luis.canyoningApp.infrastructure.mapper;

import es.luis.canyoningApp.domain.model.Ad;
import es.luis.canyoningApp.infrastructure.entity.AdEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AdMapper {

  Ad adEntityToAd(AdEntity adEntity);
}
