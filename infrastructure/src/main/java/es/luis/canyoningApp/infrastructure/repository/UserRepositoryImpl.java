package es.luis.canyoningApp.infrastructure.repository;

import es.luis.canyoningApp.domain.exception.NotFoundException;
import es.luis.canyoningApp.domain.model.Token;
import es.luis.canyoningApp.domain.model.User;
import es.luis.canyoningApp.domain.repository.UserRepository;
import es.luis.canyoningApp.infrastructure.entity.TokenUpdatePasswordEntity;
import es.luis.canyoningApp.infrastructure.entity.TokenValidateUserEntity;
import es.luis.canyoningApp.infrastructure.jpaRepository.TokenUpdatePasswordEntityRepository;
import es.luis.canyoningApp.infrastructure.jpaRepository.TokenValidateUserEntityRepository;
import es.luis.canyoningApp.infrastructure.jpaRepository.UserEntityRepository;
import es.luis.canyoningApp.infrastructure.mapper.UserRepositoryMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Transactional
@Repository
public class UserRepositoryImpl extends BaseRepository implements UserRepository {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    TokenUpdatePasswordEntityRepository tokenUpdatePasswordEntityRepository;

    @Autowired
    private UserRepositoryMapper userRepositoryMapper;

    @Autowired
    private TokenValidateUserEntityRepository tokenValidateUserEntityRepository;

    @Override
    public User createUser(User user) {
        return userRepositoryMapper.userEntityToUser(
                userEntityRepository.save(userRepositoryMapper.userToUserEntity(user)));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepositoryMapper.userEntityToUser(
                userEntityRepository
                        .findUserByEmail(email)
                        .orElseThrow(
                                () ->
                                        new NotFoundException(
                                                "Usuario no encontrado",
                                                new Throwable(
                                                        "El email es incorrecto, pruebe con otro o crea una nueva cuenta"))));
    }

    @Override
    public Page<User> getUsers(String email, String name, String location, Pageable pageable) {
        return userEntityRepository
                .getUsers(email, name, location, pageable)
                .map(userRepositoryMapper::userEntityToUser);
    }

    @Override
    public void deleteUser(User user) {
        userEntityRepository.save(userRepositoryMapper.userToUserEntity(user));
    }

    @Override
    public User updateUser(User databaseUser) {
        return userRepositoryMapper.userEntityToUser(
                userEntityRepository.save(userRepositoryMapper.userToUserEntity(databaseUser)));
    }

    @Override
    public boolean validatePasswordResetToken(Long userId, String token) {
        return tokenUpdatePasswordEntityRepository.validatePasswordResetToken(
                userId, token, OffsetDateTime.now())
                > 0;
    }

    @Override
    public void createTokenUpdatePassword(Long userId, String token, OffsetDateTime localDateTime) {
        tokenUpdatePasswordEntityRepository.save(
                TokenUpdatePasswordEntity.builder()
                        .userId(userId)
                        .token(token)
                        .deathTime(localDateTime)
                        .build());
    }

    @Override
    public void createTokenValidateUser(Long userId, String token, OffsetDateTime localDateTime) {
        tokenValidateUserEntityRepository.save(
                TokenValidateUserEntity.builder()
                        .userId(userId)
                        .token(token)
                        .deathTime(localDateTime)
                        .build());
    }

    @Override
    public void setUsedToken(Long userId, String token, Boolean used) {
        tokenUpdatePasswordEntityRepository.save(
                TokenUpdatePasswordEntity.builder().userId(userId).token(token).used(used).build());
    }

    @Override
    public User findUserById(Long userId) {
        return userRepositoryMapper.userEntityToUser(
                userEntityRepository.findById(userId).orElseThrow());
    }

    @Override
    public Token getValidateUserToken(String token) {
        return userRepositoryMapper.tokenValidateUserEntityToToken(
                tokenValidateUserEntityRepository.findByToken(token, OffsetDateTime.now()));
    }

    @Override
    public void updateTokenValidateUser(Token validateUserToken) {
        tokenValidateUserEntityRepository.save(
                userRepositoryMapper.tokenToTokenValidateUser(validateUserToken));
    }

    @Override
    public User findUserByEmailNoException(String email) {
        return userRepositoryMapper.userEntityToUser(
                userEntityRepository
                        .findUserByEmail(email)
                        .orElse(null));
    }
}
