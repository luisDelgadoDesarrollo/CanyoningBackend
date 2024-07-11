package es.luis.canyoningApp.domain.service;

import es.luis.canyoningApp.domain.exception.ExistsException;
import es.luis.canyoningApp.domain.model.User;
import es.luis.canyoningApp.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Locale;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public List<User> getUsers(String email, String name, String location) {
        return userRepository.getUsers(email, name, location);
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
        // login BCrypt.checkpw(user.getPassword(), hashpw); para comprobar la contrsena
        User user = userRepository.findUserByEmail(email);
        return BCrypt.checkpw(password, user.getPassword());
    }
}
