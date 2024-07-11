package es.luis.canyoningApp.application.rest.controller;

import es.luis.canyoningApp.application.rest.mapper.UserControllerMapper;
import es.luis.canyoningApp.canyoningApp_application.rest.api.UserApi;
import es.luis.canyoningApp.canyoningApp_application.rest.model.SimpleUserDto;
import es.luis.canyoningApp.canyoningApp_application.rest.model.UserCreateDto;
import es.luis.canyoningApp.canyoningApp_application.rest.model.UserOutDto;
import es.luis.canyoningApp.domain.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class UserController extends BaseController implements UserApi {

  @Autowired private UserService userService;

  @Autowired private UserControllerMapper userControllerMapper;

  @Override
  public ResponseEntity<UserOutDto> createUser(UserCreateDto userCreateDto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            userControllerMapper.userToUserOutDto(
                userService.createUser(userControllerMapper.userCreateDtoTuUser(userCreateDto))));
  }

  @Override
  public ResponseEntity<Void> deleteUser(Long userId) {
    userService.deleteUser(userId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Override
  public ResponseEntity<UserOutDto> getUser(Long userId) {
    return ResponseEntity.ok(userControllerMapper.userToUserOutDto(userService.getUser(userId)));
  }

  @Override
  public ResponseEntity<List<SimpleUserDto>> getUsers(String email, String name, String location) {
    return ResponseEntity.ok(
        userService.getUsers(email, name, location).stream()
            .map(userControllerMapper::userToSimpleUserDto)
            .toList());
  }

  @Override
  public ResponseEntity<Boolean> login(String email, String password) {
    return ResponseEntity.ok(userService.login(email, password));
  }

  @Override
  public ResponseEntity<Void> updatePassword(String email, String token) {
    return null;
  }

  @Override
  public ResponseEntity<List<SimpleUserDto>> updatePlan(Long userId, Integer plan) {
    return null;
  }

  @Override
  public ResponseEntity<SimpleUserDto> updateUser(Long userId, SimpleUserDto simpleUserDto) {
    return ResponseEntity.ok(
        userControllerMapper.userToSimpleUserDto(
            userService.updateUser(
                userId, userControllerMapper.simpleUserDtoToUser(simpleUserDto))));
  }
}
