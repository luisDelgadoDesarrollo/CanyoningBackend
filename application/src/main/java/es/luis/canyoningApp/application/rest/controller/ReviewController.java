package es.luis.canyoningApp.application.rest.controller;

import es.luis.canyoningApp.canyoningApp_application.rest.api.ReviewApi;
import es.luis.canyoningApp.canyoningApp_application.rest.model.ReviewDto;
import es.luis.canyoningApp.canyoningApp_application.rest.model.SimpleReviewDto;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class ReviewController implements ReviewApi {
  @Override
  public ResponseEntity<ReviewDto> createReview(ReviewDto reviewDto) {
    return null;
  }

  @Override
  public ResponseEntity<Void> deleteReview(Long reviewId) {
    return null;
  }

  @Override
  public ResponseEntity<List<ReviewDto>> getReview(Long reviewId) {
    return null;
  }

  @Override
  public ResponseEntity<List<SimpleReviewDto>> getReviews(String name) {
    return null;
  }

  @Override
  public ResponseEntity<Void> insertReview(Long reviewId, Long userId) {
    return null;
  }

  @Override
  public ResponseEntity<Void> shareReview(Long reviewId) {
    return null;
  }

  @Override
  public ResponseEntity<ReviewDto> updateReview(Long reviewId, ReviewDto reviewDto) {
    return null;
  }
}
