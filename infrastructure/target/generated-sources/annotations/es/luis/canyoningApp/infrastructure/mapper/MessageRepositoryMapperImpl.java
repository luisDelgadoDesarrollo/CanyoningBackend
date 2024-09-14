package es.luis.canyoningApp.infrastructure.mapper;

import es.luis.canyoningApp.domain.model.Message;
import es.luis.canyoningApp.domain.model.User;
import es.luis.canyoningApp.infrastructure.entity.CanyonMessageEntity;
import es.luis.canyoningApp.infrastructure.entity.SimpleCanyonEntity;
import es.luis.canyoningApp.infrastructure.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-13T16:12:36+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Amazon.com Inc.)"
)
@Component
public class MessageRepositoryMapperImpl implements MessageRepositoryMapper {

    @Override
    public Message canyonMessageEntityToMessage(CanyonMessageEntity canyonMessageEntity) {
        if ( canyonMessageEntity == null ) {
            return null;
        }

        Message.MessageBuilder<?, ?> message = Message.builder();

        message.placeId( canyonMessageEntityCanyonCanyonId( canyonMessageEntity ) );
        message.messageId( canyonMessageEntity.getCanyonMessageId() );
        message.date( canyonMessageEntity.getSendAt() );
        message.user( userEntityToUser( canyonMessageEntity.getUser() ) );
        message.message( canyonMessageEntity.getMessage() );

        message.typePlace( es.luis.canyoningApp.domain.model.ActivityType.CANYON );

        return message.build();
    }

    @Override
    public CanyonMessageEntity messageToCanyonMessageEntity(Message message) {
        if ( message == null ) {
            return null;
        }

        CanyonMessageEntity canyonMessageEntity = new CanyonMessageEntity();

        canyonMessageEntity.setCanyon( messageToSimpleCanyonEntity( message ) );
        canyonMessageEntity.setCanyonMessageId( message.getMessageId() );
        canyonMessageEntity.setUser( userToUserEntity( message.getUser() ) );
        canyonMessageEntity.setMessage( message.getMessage() );

        return canyonMessageEntity;
    }

    private Long canyonMessageEntityCanyonCanyonId(CanyonMessageEntity canyonMessageEntity) {
        if ( canyonMessageEntity == null ) {
            return null;
        }
        SimpleCanyonEntity canyon = canyonMessageEntity.getCanyon();
        if ( canyon == null ) {
            return null;
        }
        Long canyonId = canyon.getCanyonId();
        if ( canyonId == null ) {
            return null;
        }
        return canyonId;
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

    protected SimpleCanyonEntity messageToSimpleCanyonEntity(Message message) {
        if ( message == null ) {
            return null;
        }

        SimpleCanyonEntity simpleCanyonEntity = new SimpleCanyonEntity();

        simpleCanyonEntity.setCanyonId( message.getPlaceId() );

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
}
