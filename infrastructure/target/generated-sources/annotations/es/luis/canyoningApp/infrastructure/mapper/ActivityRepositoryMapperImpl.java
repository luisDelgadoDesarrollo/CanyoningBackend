package es.luis.canyoningApp.infrastructure.mapper;

import es.luis.canyoningApp.domain.model.Activity;
import es.luis.canyoningApp.domain.model.User;
import es.luis.canyoningApp.infrastructure.entity.ActivityEntity;
import es.luis.canyoningApp.infrastructure.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-02T13:04:45+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Amazon.com Inc.)"
)
@Component
public class ActivityRepositoryMapperImpl implements ActivityRepositoryMapper {

    @Override
    public ActivityEntity activityToActivityEntity(Activity activity) {
        if ( activity == null ) {
            return null;
        }

        ActivityEntity activityEntity = new ActivityEntity();

        activityEntity.setActivityId( activity.getActivityId() );
        activityEntity.setUser( userToUserEntity( activity.getUser() ) );
        activityEntity.setDate( activity.getDate() );
        activityEntity.setCapacity( activity.getCapacity() );
        activityEntity.setActivityType( activity.getActivityType() );
        activityEntity.setMeetingPlace( activity.getMeetingPlace() );
        activityEntity.setMeetingTime( activity.getMeetingTime() );
        activityEntity.setQr( activity.getQr() );
        activityEntity.setDescription( activity.getDescription() );
        activityEntity.setDeleteAt( activity.getDeleteAt() );

        return activityEntity;
    }

    @Override
    public Activity activityEntityToActivity(ActivityEntity save) {
        if ( save == null ) {
            return null;
        }

        Activity.ActivityBuilder activity = Activity.builder();

        activity.activityId( save.getActivityId() );
        activity.user( userEntityToUser( save.getUser() ) );
        activity.date( save.getDate() );
        activity.meetingPlace( save.getMeetingPlace() );
        activity.activityType( save.getActivityType() );
        activity.capacity( save.getCapacity() );
        activity.meetingTime( save.getMeetingTime() );
        activity.description( save.getDescription() );
        activity.qr( save.getQr() );
        activity.deleteAt( save.getDeleteAt() );

        return activity.build();
    }

    protected UserEntity userToUserEntity(User user) {
        if ( user == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUserId( user.getUserId() );
        userEntity.setPassword( user.getPassword() );
        userEntity.setEmail( user.getEmail() );
        userEntity.setName( user.getName() );
        userEntity.setLastName( user.getLastName() );
        userEntity.setBirthDay( user.getBirthDay() );
        userEntity.setLocation( user.getLocation() );
        userEntity.setDescription( user.getDescription() );
        userEntity.setPlan( user.getPlan() );
        if ( user.getGuia() != null ) {
            userEntity.setGuia( user.getGuia() );
        }
        userEntity.setDeleteAt( user.getDeleteAt() );

        return userEntity;
    }

    protected User userEntityToUser(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.userId( userEntity.getUserId() );
        user.password( userEntity.getPassword() );
        user.email( userEntity.getEmail() );
        user.name( userEntity.getName() );
        user.lastName( userEntity.getLastName() );
        user.birthDay( userEntity.getBirthDay() );
        user.location( userEntity.getLocation() );
        user.description( userEntity.getDescription() );
        user.plan( userEntity.getPlan() );
        user.guia( userEntity.isGuia() );
        user.deleteAt( userEntity.getDeleteAt() );

        return user.build();
    }
}
