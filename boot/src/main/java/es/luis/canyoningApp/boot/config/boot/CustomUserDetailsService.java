package es.luis.canyoningApp.boot.config.boot;

import es.luis.canyoningApp.domain.model.User;
import es.luis.canyoningApp.domain.service.UserService;
import es.luis.canyoningApp.domain.util.MapperUtils;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Log
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired private UserService userService;

  @Autowired MapperUtils mapperUtils;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info(username);
    User userByEmail = userService.getUserByEmail(username);
    return mapperUtils.userToAuthenticatedUser(userByEmail);
  }
}
