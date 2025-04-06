package es.luis.canyoningApp.infrastructure.mapper;

import es.luis.canyoningApp.domain.model.CanyonReview;
import es.luis.canyoningApp.infrastructure.entity.CanyonReviewEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ReviewRepositoryMapper {

    CanyonReviewEntity reviewToReviewEntity(CanyonReview canyonReview);

    CanyonReview reviewEntityToReview(CanyonReviewEntity save);
}
