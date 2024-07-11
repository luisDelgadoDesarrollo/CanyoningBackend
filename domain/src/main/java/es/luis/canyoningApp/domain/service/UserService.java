package es.luis.canyoningApp.domain.service;

import es.luis.canyoningApp.domain.model.User;
import java.util.List;

public interface UserService {
  User createUser(User user);

  User getUser(Long userId);

  User getUserByEmail(String email);

  List<User> getUsers(String email, String name, String location);

  void deleteUser(Long userId);

  User updateUser(Long userId, User user);

  Boolean login(String email, String password);
}
