package es.luis.canyoningApp.domain.util;

import es.luis.canyoningApp.domain.model.User;
import es.luis.canyoningApp.domain.security.AuthenticatedUser;
import org.mapstruct.Mapper;

@Mapper
public interface MapperUtils {

  AuthenticatedUser userToAuthenticatedUser(User userByEmail);
}
