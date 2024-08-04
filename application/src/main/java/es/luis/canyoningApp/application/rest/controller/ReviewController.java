package es.luis.canyoningApp.application.rest.controller;

import es.luis.canyoningApp.application.rest.mapper.CanyonReviewControllerMapper;
import es.luis.canyoningApp.application.rest.mapper.UserControllerMapper;
import es.luis.canyoningApp.canyoningApp_application.rest.api.ReviewApi;
import es.luis.canyoningApp.canyoningApp_application.rest.model.CanyonReviewDto;
import es.luis.canyoningApp.canyoningApp_application.rest.model.CanyonReviewResponseDto;
import es.luis.canyoningApp.canyoningApp_application.rest.model.SimpleCanyonReviewDto;
import es.luis.canyoningApp.domain.model.CanyonReview;
import es.luis.canyoningApp.domain.service.CanyonReviewService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class ReviewController extends BaseController implements ReviewApi {

  @Autowired private CanyonReviewService canyonReviewService;

  @Autowired private CanyonReviewControllerMapper canyonReviewControllerMapper;

  @Autowired private UserControllerMapper userControllerMapper;

  @Override
  public ResponseEntity<CanyonReviewResponseDto> createCanyonReview(
      CanyonReviewDto canyonReviewDto) {
    return ResponseEntity.ok(
        canyonReviewControllerMapper.canyonReviewToCanyonReviewResponseDto(
            canyonReviewService.createReview(
                canyonReviewControllerMapper.canyonReviewDtoToCanyonReview(canyonReviewDto))));
  }

  @Override
  public ResponseEntity<Void> deleteCanyonReview(Long canyonReviewId) {
    canyonReviewService.deleteReview(canyonReviewId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Override
  public ResponseEntity<CanyonReviewResponseDto> getCanyonReview(Long canyonReviewId) {
    return ResponseEntity.ok(
        canyonReviewControllerMapper.canyonReviewToCanyonReviewResponseDto(
            canyonReviewService.getCanyonReview(canyonReviewId)));
  }

  @Override
  public ResponseEntity<List<SimpleCanyonReviewDto>> getCanyonReviews(
      Long canyonId, Integer page, Integer size, String sort, Pageable pageable) {

    Page<CanyonReview> reviews = canyonReviewService.getReviews(canyonId, pageable);
    addPaginationHeadersToResponse(reviews);

    return ResponseEntity.ok(
        reviews.getContent().stream()
            .map(canyonReviewControllerMapper::canyonReviewToSimpleCanyonReviewDto)
            .toList());
  }

  @Override
  public ResponseEntity<Void> insertUserIntoCanyonReview(Long reviewId, List<Long> users) {
    canyonReviewService.insertUserIntoCanyonReview(reviewId, users);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Override
  public ResponseEntity<Void> shareCanyonReview(Long reviewId) {
    return null;
  }

  @Override
  public ResponseEntity<CanyonReviewResponseDto> updateCanyonReview(
      Long canyonReviewId, CanyonReviewDto canyonReviewDto) {
    return ResponseEntity.ok(
        canyonReviewControllerMapper.canyonReviewToCanyonReviewResponseDto(
            canyonReviewService.updateReview(
                canyonReviewId,
                canyonReviewControllerMapper.canyonReviewDtoToCanyonReview(canyonReviewDto))));
  }
}
