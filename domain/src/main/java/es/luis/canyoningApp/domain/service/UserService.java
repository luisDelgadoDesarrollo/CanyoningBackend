package es.luis.canyoningApp.domain.service;

import es.luis.canyoningApp.domain.model.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
  User createUser(User user);

  User getUser(Long userId);

  User getUserByEmail(String email);

  Page<User> getUsers(String email, String name, String location, Pageable pageable);

  void deleteUser(Long userId);

  User updateUser(Long userId, User user);

  Boolean login(String email, String password);

  void updatePassword(String email, String token, String password);

  void requestUpdatePassword(String email);

  List<User> getUsersFromReview(Long reviewId);
}
