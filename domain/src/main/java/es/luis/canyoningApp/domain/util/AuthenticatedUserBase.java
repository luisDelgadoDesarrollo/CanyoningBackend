package es.luis.canyoningApp.domain.util;

import es.luis.canyoningApp.domain.exception.ForbiddenException;
import es.luis.canyoningApp.domain.security.AuthenticatedUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Base class to access authenticated user information
 */
public abstract class AuthenticatedUserBase {

    /**
     * @return authenticated user data
     */
    protected AuthenticatedUser getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new ForbiddenException(
                    "User not authenticated.",
                    new Throwable("There is no authenticated user in the session context."));
        }
        return (AuthenticatedUser) authentication.getPrincipal();
    }

    /**
     * @return authenticated user ID
     */
    protected Long getAuthenticatedUserId() {
        return getAuthenticatedUser().getUserId();
    }

    protected String getAuthenticatedUserEmail() {
        return getAuthenticatedUser().getEmail();
    }

    /**
     * @return authenticated username
     */
    protected String getAuthenticatedUsername() {
        return getAuthenticatedUser().getUsername();
    }
}
