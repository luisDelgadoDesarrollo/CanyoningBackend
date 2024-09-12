package es.luis.canyoningApp.infrastructure.mapper;

import es.luis.canyoningApp.domain.model.CanyonReview;
import es.luis.canyoningApp.domain.model.SimpleCanyon;
import es.luis.canyoningApp.domain.model.User;
import es.luis.canyoningApp.infrastructure.entity.CanyonReviewEntity;
import es.luis.canyoningApp.infrastructure.entity.SimpleCanyonEntity;
import es.luis.canyoningApp.infrastructure.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-12T11:40:52+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Amazon.com Inc.)"
)
@Component
public class ReviewRepositoryMapperImpl implements ReviewRepositoryMapper {

    @Override
    public CanyonReviewEntity reviewToReviewEntity(CanyonReview canyonReview) {
        if ( canyonReview == null ) {
            return null;
        }

        CanyonReviewEntity canyonReviewEntity = new CanyonReviewEntity();

        canyonReviewEntity.setCanyonReviewId( canyonReview.getCanyonReviewId() );
        canyonReviewEntity.setCanyon( simpleCanyonToSimpleCanyonEntity( canyonReview.getCanyon() ) );
        canyonReviewEntity.setUser( userToUserEntity( canyonReview.getUser() ) );
        canyonReviewEntity.setDuration( canyonReview.getDuration() );
        canyonReviewEntity.setCombinedCar( canyonReview.getCombinedCar() );
        canyonReviewEntity.setDescription( canyonReview.getDescription() );
        canyonReviewEntity.setQr( canyonReview.getQr() );
        canyonReviewEntity.setDate( canyonReview.getDate() );
        canyonReviewEntity.setCaudal( canyonReview.getCaudal() );
        canyonReviewEntity.setDeleteAt( canyonReview.getDeleteAt() );

        return canyonReviewEntity;
    }

    @Override
    public CanyonReview reviewEntityToReview(CanyonReviewEntity save) {
        if ( save == null ) {
            return null;
        }

        CanyonReview.CanyonReviewBuilder canyonReview = CanyonReview.builder();

        canyonReview.canyonReviewId( save.getCanyonReviewId() );
        canyonReview.canyon( simpleCanyonEntityToSimpleCanyon( save.getCanyon() ) );
        canyonReview.user( userEntityToUser( save.getUser() ) );
        canyonReview.date( save.getDate() );
        canyonReview.duration( save.getDuration() );
        canyonReview.combinedCar( save.getCombinedCar() );
        canyonReview.description( save.getDescription() );
        canyonReview.qr( save.getQr() );
        canyonReview.caudal( save.getCaudal() );
        canyonReview.deleteAt( save.getDeleteAt() );

        return canyonReview.build();
    }

    protected SimpleCanyonEntity simpleCanyonToSimpleCanyonEntity(SimpleCanyon simpleCanyon) {
        if ( simpleCanyon == null ) {
            return null;
        }

        SimpleCanyonEntity simpleCanyonEntity = new SimpleCanyonEntity();

        simpleCanyonEntity.setCanyonId( simpleCanyon.getCanyonId() );
        simpleCanyonEntity.setName( simpleCanyon.getName() );
        simpleCanyonEntity.setSeason( simpleCanyon.getSeason() );
        simpleCanyonEntity.setDescription( simpleCanyon.getDescription() );
        simpleCanyonEntity.setCroquis( simpleCanyon.getCroquis() );
        simpleCanyonEntity.setRiver( simpleCanyon.getRiver() );
        simpleCanyonEntity.setPopulation( simpleCanyon.getPopulation() );
        simpleCanyonEntity.setAccess( simpleCanyon.getAccess() );
        simpleCanyonEntity.setApproach( simpleCanyon.getApproach() );
        simpleCanyonEntity.setDescent( simpleCanyon.getDescent() );
        simpleCanyonEntity.set_return( simpleCanyon.get_return() );
        simpleCanyonEntity.setScape( simpleCanyon.getScape() );
        simpleCanyonEntity.setDeleteAt( simpleCanyon.getDeleteAt() );

        return simpleCanyonEntity;
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

    protected SimpleCanyon simpleCanyonEntityToSimpleCanyon(SimpleCanyonEntity simpleCanyonEntity) {
        if ( simpleCanyonEntity == null ) {
            return null;
        }

        SimpleCanyon.SimpleCanyonBuilder<?, ?> simpleCanyon = SimpleCanyon.builder();

        simpleCanyon.canyonId( simpleCanyonEntity.getCanyonId() );
        simpleCanyon.name( simpleCanyonEntity.getName() );
        simpleCanyon.croquis( simpleCanyonEntity.getCroquis() );
        simpleCanyon.description( simpleCanyonEntity.getDescription() );
        simpleCanyon.season( simpleCanyonEntity.getSeason() );
        simpleCanyon.river( simpleCanyonEntity.getRiver() );
        simpleCanyon.population( simpleCanyonEntity.getPopulation() );
        simpleCanyon.access( simpleCanyonEntity.getAccess() );
        simpleCanyon.approach( simpleCanyonEntity.getApproach() );
        simpleCanyon.descent( simpleCanyonEntity.getDescent() );
        simpleCanyon._return( simpleCanyonEntity.get_return() );
        simpleCanyon.scape( simpleCanyonEntity.getScape() );
        simpleCanyon.deleteAt( simpleCanyonEntity.getDeleteAt() );

        return simpleCanyon.build();
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
