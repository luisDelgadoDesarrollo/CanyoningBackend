package es.luis.canyoningApp.domain.service;

import es.luis.canyoningApp.domain.exception.ConflictException;
import es.luis.canyoningApp.domain.exception.ExistsException;
import es.luis.canyoningApp.domain.model.User;
import es.luis.canyoningApp.domain.repository.CanyonReviewRepository;
import es.luis.canyoningApp.domain.repository.UserRepository;
import es.luis.canyoningApp.domain.util.ServiceUtils;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserServiceImpl extends BaseService implements UserService {

  @Autowired private UserRepository userRepository;

  @Autowired private PasswordEncoder passwordEncoder;

  @Autowired private CanyonReviewRepository canyonReviewRepository;

  @Autowired private SendEmail sendEmail;

  @Override
  public User createUser(User user) {

    user.setEmail(user.getEmail().toLowerCase(Locale.ROOT));
    if (ObjectUtils.isEmpty(getUserByEmail(user.getEmail().toLowerCase(Locale.ROOT)))) {
      user.setPlan(0);
      user.setGuia(false);
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      return userRepository.createUser(user);
    }
    throw new ExistsException("Usuario existente", new Throwable("El usuario ya esta registrado"));
  }

  @Override
  public User getUser(Long userId) {
    return userRepository.getUserById(userId);
  }

  @Override
  public User getUserByEmail(String email) {
    return userRepository.findUserByEmail(email);
  }

  @Override
  public Page<User> getUsers(String email, String name, String location, Pageable pageable) {
    return userRepository.getUsers(email, name, location, pageable);
  }

  @Override
  public void deleteUser(Long userId) {
    User user = getUser(userId);
    user.setDeleteAt(OffsetDateTime.now());
    user.setPassword("");
    userRepository.deleteUser(user);
  }

  @Override
  public User updateUser(Long userId, User user) {
    User databaseUser = userRepository.getUserById(userId);
    databaseUser.setName(user.getName());
    databaseUser.setLastName(user.getLastName());
    databaseUser.setLocation(user.getLocation());
    databaseUser.setDescription(user.getDescription());
    databaseUser.setBirthDay(user.getBirthDay());
    return userRepository.updateUser(databaseUser);
  }

  @Override
  public Boolean login(String email, String password) {
    User user = userRepository.findUserByEmail(email);
    return passwordEncoder.matches(password, user.getPassword());
  }

  @Override
  public void updatePassword(String email, String token, String password) {
    User user = getUserByEmail(email);

    if (userRepository.validatePasswordResetToken(user.getUserId(), token)) {
      user.setPassword(passwordEncoder.encode(password));
      userRepository.createUser(user);
      userRepository.setUsedToken(user.getUserId(), token, true);
    } else {
      throw new ConflictException("Token used", new Throwable("The token has been already used"));
    }
  }

  @Override
  public void requestUpdatePassword(String email) {
    User user = getUserByEmail(email);
    String token = ServiceUtils.generateToken();
    sendEmail.sendPasswordEmail(email, token);
    userRepository.createTokenUpdatePassword(
        user.getUserId(), token, ServiceUtils.calculateExpiration());
  }

  @Override
  public List<User> getUsersFromReview(Long reviewId) {
    return canyonReviewRepository.getUsers(reviewId);
  }
}
