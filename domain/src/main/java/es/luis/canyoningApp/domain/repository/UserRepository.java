package es.luis.canyoningApp.domain.repository;

import es.luis.canyoningApp.domain.model.User;
import java.util.List;

public interface UserRepository {
  User createUser(User user);

  User findUserByEmail(String email);

  User getUserById(Long userId);

  List<User> getUsers(String email, String name, String location);

  void deleteUser(User user);

  User updateUser(User databaseUser);
}
