package es.luis.canyoningApp.domain.service;

import es.luis.canyoningApp.domain.exception.ForbiddenException;
import es.luis.canyoningApp.domain.model.CanyonReview;
import es.luis.canyoningApp.domain.repository.CanyonReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.xml.transform.TransformerException;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class CanyonReviewServiceImpl extends BaseService implements CanyonReviewService {

    @Autowired
    CanyonReviewRepository canyonReviewRepository;

    @Autowired
    UserService userService;

    @Override
    public CanyonReview createReview(CanyonReview canyonReview) {

        // todo generar qr
        canyonReview.setQr("crear qr");
        canyonReview.setUser(getAuthenticatedUser());
        CanyonReview canyonReviewSaved = canyonReviewRepository.createReview(canyonReview);

        canyonReviewRepository.deleteReviewUserByReview(canyonReviewSaved.getCanyonReviewId());
        canyonReview
                .getUsers()
                .forEach(
                        user ->
                                canyonReviewRepository.createReviewUser(
                                        canyonReviewSaved.getCanyonReviewId(), user));
        canyonReviewSaved.setUsers(canyonReview.getUsers());
        return canyonReviewSaved;
    }

    @Override
    public CanyonReview getCanyonReview(Long reviewId) {
        CanyonReview canyonReview = canyonReviewRepository.getReviewById(reviewId);
        canyonReview.setUsers(userService.getUsersFromReview(reviewId));
        return canyonReview;
    }

    @Override
    public Page<CanyonReview> getReviews(Long canyonId, Pageable pageable) {
        return canyonReviewRepository.getCanyonReviews(canyonId, pageable);
    }

    @Override
    public void deleteReview(Long canyonReviewId) {
        CanyonReview canyonReview = getCanyonReview(canyonReviewId);
        if (getAuthenticatedUserId().equals(canyonReview.getUser().getUserId())) {
            canyonReview.setDeleteAt(OffsetDateTime.now());
            canyonReviewRepository.deleteReview(canyonReview);
            return;
        }
        throw new ForbiddenException(
                "No puedes borrar esta review",
                new TransformerException("No puedes borrar esta revio por que no eres el autor de ella"));
    }

    @Override
    public CanyonReview updateReview(Long reviewId, CanyonReview canyonReview) {

        CanyonReview canyonReviewSaved = getCanyonReview(reviewId);
        if (getAuthenticatedUserId().equals(canyonReviewSaved.getUser().getUserId())) {
            canyonReviewSaved.setDate(canyonReview.getDate());
            canyonReviewSaved.setDuration(canyonReview.getDuration());
            canyonReviewSaved.setCombinedCar(canyonReview.getCombinedCar());
            canyonReviewSaved.setDescription(canyonReview.getDescription());
            canyonReviewSaved.setCaudal(canyonReview.getCaudal());
            canyonReviewSaved.setUsers(canyonReview.getUsers());
            return createReview(canyonReviewSaved);
        }
        throw new ForbiddenException(
                "No puedes actualizar esta review",
                new TransformerException(
                        "No puedes actualizar esta revio por que no eres el autor de ella"));
    }

    @Override
    public void insertUserIntoCanyonReview(Long reviewId, List<Long> users) {
        canyonReviewRepository.insertUserIntoCanyonReview(reviewId, users);
    }
}
