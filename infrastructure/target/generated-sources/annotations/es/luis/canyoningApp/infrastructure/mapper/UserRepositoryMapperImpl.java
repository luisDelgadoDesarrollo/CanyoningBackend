package es.luis.canyoningApp.infrastructure.mapper;

import es.luis.canyoningApp.domain.model.User;
import es.luis.canyoningApp.infrastructure.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-25T13:17:39+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Amazon.com Inc.)"
)
@Component
public class UserRepositoryMapperImpl implements UserRepositoryMapper {

    @Override
    public UserEntity userToUserEntity(User user) {
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

    @Override
    public User userEntityToUser(UserEntity userEntity) {
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
