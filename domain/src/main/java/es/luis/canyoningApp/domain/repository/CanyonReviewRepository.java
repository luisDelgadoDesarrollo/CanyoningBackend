package es.luis.canyoningApp.domain.repository;

import es.luis.canyoningApp.domain.model.CanyonReview;
import es.luis.canyoningApp.domain.model.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CanyonReviewRepository {
  CanyonReview createReview(CanyonReview requestCanyonReview);

  void createReviewUser(Long reviewId, User user);

  CanyonReview getReviewById(Long reviewId);

  Page<CanyonReview> getCanyonReviews(Long canyonId, Pageable pageable);

  void deleteReview(CanyonReview canyonReview);

  CanyonReview updateReview(CanyonReview canyonReview);

  void deleteReviewUserByReview(Long reviewId);

  List<User> getUsers(Long reviewId);

  void insertUserIntoCanyonReview(Long reviewId, List<Long> users);
}
