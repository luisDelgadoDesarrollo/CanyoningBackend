package es.luis.canyoningApp.application.rest.mapper;

import es.luis.canyoningApp.application.rest.model.CanyonReviewDto;
import es.luis.canyoningApp.application.rest.model.CanyonReviewResponseDto;
import es.luis.canyoningApp.application.rest.model.SimpleCanyonReviewDto;
import es.luis.canyoningApp.domain.model.CanyonReview;
import org.mapstruct.Mapper;

@Mapper
public interface CanyonReviewControllerMapper {

  CanyonReview canyonReviewDtoToCanyonReview(CanyonReviewDto canyonReviewDto);

  CanyonReviewResponseDto canyonReviewToCanyonReviewResponseDto(CanyonReview review);

  SimpleCanyonReviewDto canyonReviewToSimpleCanyonReviewDto(CanyonReview canyonReview);
}
