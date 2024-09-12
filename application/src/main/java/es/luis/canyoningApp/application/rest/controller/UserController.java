package es.luis.canyoningApp.application.rest.controller;

import es.luis.canyoningApp.application.rest.mapper.UserControllerMapper;
import es.luis.canyoningApp.canyoningApp_application.rest.api.UserApi;
import es.luis.canyoningApp.canyoningApp_application.rest.model.SimpleUserDto;
import es.luis.canyoningApp.canyoningApp_application.rest.model.UserCreateDto;
import es.luis.canyoningApp.canyoningApp_application.rest.model.UserOutDto;
import es.luis.canyoningApp.domain.model.User;
import es.luis.canyoningApp.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController extends BaseController implements UserApi {

    @Autowired
    private UserService userService;

    @Autowired
    private UserControllerMapper userControllerMapper;

    @Override
    public ResponseEntity<UserOutDto> createUser(UserCreateDto userCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        userControllerMapper.userToUserOutDto(
                                userService.createUser(userControllerMapper.userCreateDtoTuUser(userCreateDto))));
    }

    @Override
    public ResponseEntity<Void> deleteUser(String email) {
        userService.deleteUser(email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<UserOutDto> getUser(String email) {
        return ResponseEntity.ok(
                userControllerMapper.userToUserOutDto(userService.getUserByEmail(email)));
    }

    @Override
    public ResponseEntity<List<SimpleUserDto>> getUsers(
            String email,
            String name,
            String location,
            Integer page,
            Integer size,
            String sort,
            Pageable pageable) {
        Page<User> users = userService.getUsers(email, name, location, pageable);
        addPaginationHeadersToResponse(users);
        return ResponseEntity.ok(
                users.stream().map(userControllerMapper::userToSimpleUserDto).toList());
    }

    @Override
    public ResponseEntity<Boolean> login(String email, String password) {
        return ResponseEntity.ok(userService.login(email, password));
    }

    @Override
    public ResponseEntity<Void> requestUpdatePassword(String email) {
        userService.requestUpdatePassword(email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<Void> updatePassword(String email, String token, String password) {
        userService.updatePassword(email, token, password);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<List<SimpleUserDto>> updatePlan(String email, Integer plan) {
        // todo cuando piense como se paga
        return null;
    }

    @Override
    public ResponseEntity<SimpleUserDto> updateUser(String email, SimpleUserDto simpleUserDto) {
        return ResponseEntity.ok(
                userControllerMapper.userToSimpleUserDto(
                        userService.updateUser(
                                email, userControllerMapper.simpleUserDtoToUser(simpleUserDto))));
    }

    @GetMapping("/")
    public ResponseEntity<Boolean> check() {
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
