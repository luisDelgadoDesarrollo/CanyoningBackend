package es.luis.canyoningApp.infrastructure.repository;

import es.luis.canyoningApp.domain.model.CanyonReview;
import es.luis.canyoningApp.domain.model.User;
import es.luis.canyoningApp.domain.repository.CanyonReviewRepository;
import es.luis.canyoningApp.infrastructure.entity.CanyonReviewUserEntity;
import es.luis.canyoningApp.infrastructure.entity.PrimaryKey.ReviewUserId;
import es.luis.canyoningApp.infrastructure.jpaRepository.CanyonReviewEntityRepository;
import es.luis.canyoningApp.infrastructure.jpaRepository.CanyonReviewUserEntityRepository;
import es.luis.canyoningApp.infrastructure.mapper.ReviewRepositoryMapper;
import es.luis.canyoningApp.infrastructure.mapper.UserRepositoryMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public class CanyonReviewRepositoryImpl implements CanyonReviewRepository {

    @Autowired
    private ReviewRepositoryMapper reviewRepositoryMapper;

    @Autowired
    private UserRepositoryMapper userRepositoryMapper;

    @Autowired
    CanyonReviewEntityRepository canyonReviewEntityRepository;

    @Autowired
    CanyonReviewUserEntityRepository canyonReviewUserEntityRepository;

    @Override
    public CanyonReview createReview(CanyonReview canyonReview) {
        return reviewRepositoryMapper.reviewEntityToReview(
                canyonReviewEntityRepository.save(
                        reviewRepositoryMapper.reviewToReviewEntity(canyonReview)));
    }

    @Override
    public void createReviewUser(Long reviewId, User user) {

        canyonReviewUserEntityRepository.save(
                CanyonReviewUserEntity.builder()
                        .id(new ReviewUserId(reviewId, user.getUserId()))
                        .user(userRepositoryMapper.userToUserEntity(user))
                        .build());
    }

    @Override
    public CanyonReview getReviewById(Long reviewId) {
        return reviewRepositoryMapper.reviewEntityToReview(
                canyonReviewEntityRepository.findById(reviewId).orElseThrow());
    }

    @Override
    public Page<CanyonReview> getCanyonReviews(Long canyonId, Pageable pageable) {
        return canyonReviewEntityRepository
                .getCanyonReviews(canyonId, pageable)
                .map(reviewRepositoryMapper::reviewEntityToReview);
    }

    @Override
    public void deleteReview(CanyonReview canyonReview) {
        canyonReviewEntityRepository.save(reviewRepositoryMapper.reviewToReviewEntity(canyonReview));
    }

    @Override
    public CanyonReview updateReview(CanyonReview canyonReview) {
        return reviewRepositoryMapper.reviewEntityToReview(
                canyonReviewEntityRepository.save(
                        reviewRepositoryMapper.reviewToReviewEntity(canyonReview)));
    }

    @Override
    public void deleteReviewUserByReview(Long reviewId) {
        canyonReviewUserEntityRepository.removeAllByReviewId(reviewId);
    }

    @Override
    public List<User> getUsers(Long reviewId) {
        return canyonReviewUserEntityRepository.getUserByReviewId(reviewId).stream()
                .map(userRepositoryMapper::userEntityToUser)
                .toList();
    }

    @Override
    public void insertUserIntoCanyonReview(Long reviewId, List<Long> users) {
        users.forEach(user -> createReviewUser(reviewId, User.builder().userId(user).build()));
    }
}
