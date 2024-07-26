package es.luis.canyoningApp.domain.repository;

import es.luis.canyoningApp.domain.model.User;
import java.time.OffsetDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepository {
  User createUser(User user);

  User findUserByEmail(String email);

  User getUserById(Long userId);

  Page<User> getUsers(String email, String name, String location, Pageable pageable);

  void deleteUser(User user);

  User updateUser(User databaseUser);

  boolean validatePasswordResetToken(Long userId, String token);

  void createTokenUpdatePassword(Long userId, String token, OffsetDateTime localDateTime);

  void setUsedToken(Long userId, String token, Boolean used);
}
