package es.luis.canyoningApp.domain.service;

import es.luis.canyoningApp.domain.model.CanyonReview;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CanyonReviewService {
  CanyonReview createReview(CanyonReview CanyonReview);

  CanyonReview getCanyonReview(Long canyonReviewId);

  Page<CanyonReview> getReviews(Long canyonId, Pageable pageable);

  void deleteReview(Long canyonReviewId);

  CanyonReview updateReview(Long reviewId, CanyonReview canyonReview);

  void insertUserIntoCanyonReview(Long reviewId, List<Long> list);
}
