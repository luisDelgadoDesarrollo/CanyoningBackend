package es.luis.canyoningApp.infrastructure.mapper;

import es.luis.canyoningApp.domain.model.User;
import es.luis.canyoningApp.infrastructure.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserEntityMapper extends BaseRepositoryMapper {

  UserEntity userToUserEntity(User user);

  User userEntityToUser(UserEntity userEntity);
}
