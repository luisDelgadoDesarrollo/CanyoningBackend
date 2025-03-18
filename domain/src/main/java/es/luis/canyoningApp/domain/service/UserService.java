package es.luis.canyoningApp.domain.service;

import es.luis.canyoningApp.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User getUserByEmail(String email);

    User getUserByEmailNoException(String email);

    Page<User> getUsers(String email, String name, String location, Pageable pageable);

    void deleteUser(String email);

    User updateUser(String email, User user);

    Boolean login(String email, String password);

    void updatePassword(String email, String token, String password);

    void requestUpdatePassword(String email);

    List<User> getUsersFromReview(Long reviewId);

    User getUserById(Long userId);

    String validateUser(String token);
}
