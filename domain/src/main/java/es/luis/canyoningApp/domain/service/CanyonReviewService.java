package es.luis.canyoningApp.domain.service;

import es.luis.canyoningApp.domain.model.CanyonReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CanyonReviewService {
    CanyonReview createReview(CanyonReview CanyonReview);

    CanyonReview getCanyonReview(Long canyonReviewId);

    Page<CanyonReview> getReviews(Long canyonId, Pageable pageable);

    void deleteReview(Long canyonReviewId);

    CanyonReview updateReview(Long reviewId, CanyonReview canyonReview);

    void insertUserIntoCanyonReview(Long reviewId, List<Long> list);
}
