package es.luis.canyoningApp.application.rest.mapper;

import es.luis.canyoningApp.canyoningApp_application.rest.model.SimpleUserDto;
import es.luis.canyoningApp.canyoningApp_application.rest.model.UserCreateDto;
import es.luis.canyoningApp.canyoningApp_application.rest.model.UserOutDto;
import es.luis.canyoningApp.domain.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserControllerMapper {
  User userCreateDtoTuUser(UserCreateDto userCreateDto);

  UserOutDto userToUserOutDto(User user);

  SimpleUserDto userToSimpleUserDto(User user);

  User simpleUserDtoToUser(SimpleUserDto simpleUserDto);
}
