package es.luis.canyoningApp.domain.security;

import es.luis.canyoningApp.domain.model.User;
import java.util.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;

@SuperBuilder
public class AuthenticatedUser extends User implements UserDetails {

  public AuthenticatedUser() {
    this.authorities = getUserAuthorities();
  }

  private final Collection<GrantedAuthority> authorities;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return getUserAuthorities();
  }

  @Override
  public String getPassword() {
    return super.getPassword();
  }

  @Override
  public String getUsername() {
    return super.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return ObjectUtils.isEmpty(getDeleteAt());
  }

  @Override
  public boolean isAccountNonLocked() {
    return ObjectUtils.isEmpty(getDeleteAt());
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return ObjectUtils.isEmpty(getDeleteAt());
  }

  @Override
  public boolean isEnabled() {
    return ObjectUtils.isEmpty(getDeleteAt());
  }

  private Collection<GrantedAuthority> getUserAuthorities() {
    Set<GrantedAuthority> grantedAuthorities =
        new TreeSet<>(Comparator.comparing(GrantedAuthority::getAuthority));
    addGrantedAuthority(grantedAuthorities, RoleUtils.ROLE_AUTHENTICATED);

    // Add here all roles and authorities that the user will have after authentication

    return Collections.unmodifiableSet(grantedAuthorities);
  }

  private void addGrantedAuthority(
      Collection<GrantedAuthority> grantedAuthorities, String... authorities) {

    for (String authority : authorities) {
      grantedAuthorities.add(new SimpleGrantedAuthority(authority));
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof AuthenticatedUser user) {
      return this.getUsername().equals(user.getUsername());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.getUsername().hashCode();
  }
}
