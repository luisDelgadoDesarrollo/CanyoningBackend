package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.FavouritesCanyonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouritesCanyonEntityRepository
    extends JpaRepository<FavouritesCanyonEntity, FavouritesCanyonEntity.PrimaryKeys> {}
