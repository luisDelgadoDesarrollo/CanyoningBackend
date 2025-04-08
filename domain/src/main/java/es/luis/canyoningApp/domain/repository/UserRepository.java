package es.luis.canyoningApp.domain.repository;

import es.luis.canyoningApp.domain.model.Token;
import es.luis.canyoningApp.domain.model.User;
import java.time.OffsetDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepository {
  User createUser(User user);

  User findUserByEmail(String email);

  Page<User> getUsers(String email, String name, String location, Pageable pageable);

  void deleteUser(User user);

  User updateUser(User databaseUser);

  boolean validatePasswordResetToken(Long userId, String token);

  void createTokenUpdatePassword(Long userId, String token, OffsetDateTime localDateTime);

  void createTokenValidateUser(Long userId, String token, OffsetDateTime localDateTime);

  void deleteTokenValidateUser(Long userId);

  void setUsedToken(Long userId, String token, Boolean used);

  User findUserById(Long userId);

  Token getValidateUserToken(String token);

  void updateTokenValidateUser(Token validateUserToken);

  User findUserByEmailNoException(String email);
}
