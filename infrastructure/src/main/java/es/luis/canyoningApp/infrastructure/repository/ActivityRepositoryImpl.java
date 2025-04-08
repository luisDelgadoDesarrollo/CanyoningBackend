package es.luis.canyoningApp.infrastructure.repository;

import es.luis.canyoningApp.domain.model.Activity;
import es.luis.canyoningApp.domain.model.User;
import es.luis.canyoningApp.domain.repository.ActivityRepository;
import es.luis.canyoningApp.infrastructure.entity.ActivityUserEntity;
import es.luis.canyoningApp.infrastructure.entity.PrimaryKey.ActivityUserId;
import es.luis.canyoningApp.infrastructure.jpaRepository.ActivityEntityRepository;
import es.luis.canyoningApp.infrastructure.jpaRepository.ActivityUserEntityRepository;
import es.luis.canyoningApp.infrastructure.mapper.ActivityRepositoryMapper;
import es.luis.canyoningApp.infrastructure.mapper.UserRepositoryMapper;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class ActivityRepositoryImpl implements ActivityRepository {

  @Autowired ActivityEntityRepository activityEntityRepository;

  @Autowired private ActivityUserEntityRepository activityUserEntityRepository;

  @Autowired private ActivityRepositoryMapper activityRepositoryMapper;

  @Autowired private UserRepositoryMapper userRepositoryMapper;

  @Override
  public Activity createActivity(Activity activity) {
    return activityRepositoryMapper.activityEntityToActivity(
        activityEntityRepository.save(activityRepositoryMapper.activityToActivityEntity(activity)));
  }

  @Override
  public void insertActivityUser(Long activityId, User user) {
    activityUserEntityRepository.save(
        ActivityUserEntity.builder()
            .id(new ActivityUserId(activityId, user.getUserId()))
            .user(userRepositoryMapper.userToUserEntity(user))
            .build());
  }

  @Override
  public Activity getActivity(Long activityId) {
    return activityRepositoryMapper.activityEntityToActivity(
        activityEntityRepository.findById(activityId).orElseThrow());
  }

  @Override
  public void deleteActivityUser(Long activityId) {
    activityUserEntityRepository.removeAllByAcivityId(activityId);
  }

  @Override
  public Page<Activity> getActivities(LocalDate date, String meetingPlace, Pageable pageable) {
    return activityEntityRepository
        .getActivities(date, meetingPlace, pageable)
        .map(activityRepositoryMapper::activityEntityToActivity);
  }

  @Override
  public void cancelActivity(Activity activity) {
    activityEntityRepository.save(activityRepositoryMapper.activityToActivityEntity(activity));
  }
}
