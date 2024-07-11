package es.luis.canyoningApp.infrastructure.repository;

import es.luis.canyoningApp.domain.model.User;
import es.luis.canyoningApp.domain.repository.UserRepository;
import es.luis.canyoningApp.infrastructure.jpaRepository.UserEntityRepository;
import es.luis.canyoningApp.infrastructure.mapper.UserEntityMapper;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends BaseRepository implements UserRepository {

  @Autowired private UserEntityRepository userEntityRepository;

  @Autowired private UserEntityMapper userEntityMapper;

  @Override
  public User createUser(User user) {
    return userEntityMapper.userEntityToUser(
        userEntityRepository.save(userEntityMapper.userToUserEntity(user)));
  }

  @Override
  public User findUserByEmail(String email) {
    return userEntityMapper.userEntityToUser(userEntityRepository.findUserByEmail(email));
  }

  @Override
  public User getUserById(Long userId) {
    return userEntityMapper.userEntityToUser(
        Optional.of(userEntityRepository.findById(userId)).get().orElseThrow());
  }

  @Override
  public List<User> getUsers(String email, String name, String location) {
    return userEntityRepository.getUsers(email, name, location).stream()
        .map(userEntityMapper::userEntityToUser)
        .toList();
  }

  @Override
  public void deleteUser(User user) {
    userEntityRepository.save(userEntityMapper.userToUserEntity(user));
  }

  @Override
  public User updateUser(User databaseUser) {
    return userEntityMapper.userEntityToUser(
        userEntityRepository.save(userEntityMapper.userToUserEntity(databaseUser)));
  }
}
