package es.luis.canyoningApp.domain.util;

/**
 * Base class to access authenticated user information
 */
public abstract class AuthenticatedUserBase {

    /**
     * @return authenticated user data
     */
//    protected AuthenticatedUser getAuthenticatedUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null) {
//            throw new ForbiddenException(
//                    "User not authenticated.",
//                    new Throwable("There is no authenticated user in the session context."));
//        }
//        return (AuthenticatedUser) authentication.getPrincipal();
//    }
//
//    /**
//     * @return authenticated user ID
//     */
//    protected Long getAuthenticatedUserId() {
//        return getAuthenticatedUser().getUserId();
//    }
//
//    /**
//     * @return authenticated username
//     */
//    protected String getAuthenticatedUsername() {
//        return getAuthenticatedUser().getUsername();
//    }
}
