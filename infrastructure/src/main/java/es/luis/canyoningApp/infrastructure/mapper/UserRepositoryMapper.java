package es.luis.canyoningApp.infrastructure.mapper;

import es.luis.canyoningApp.domain.model.Token;
import es.luis.canyoningApp.domain.model.User;
import es.luis.canyoningApp.infrastructure.entity.TokenValidateUserEntity;
import es.luis.canyoningApp.infrastructure.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserRepositoryMapper extends BaseRepositoryMapper {

    UserEntity userToUserEntity(User user);

    User userEntityToUser(UserEntity userEntity);

    Token tokenValidateUserEntityToToken(TokenValidateUserEntity byToken);

    TokenValidateUserEntity tokenToTokenValidateUser(Token token);
}
