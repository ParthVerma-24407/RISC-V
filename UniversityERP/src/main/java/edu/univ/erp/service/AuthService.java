package edu.univ.erp.service;

import edu.univ.erp.auth.LoginHandler;
import edu.univ.erp.auth.PasswordHandler;
import edu.univ.erp.data.dao.User_auth_DAO;
import edu.univ.erp.domain.User_info;

/**
 * AuthService
 *
 * High-level authentication service for the rest of the app.
 * - Uses LoginHandler for login.
 * - Uses PasswordHandler for password change.
 * - No Swing / UI here.
 */
public class AuthService {

    private final User_auth_DAO userAuthDao;
    private final LoginHandler loginHandler;
    private final PasswordHandler passwordHandler;

    public AuthService(User_auth_DAO userAuthDao) {
        if (userAuthDao == null) {
            throw new IllegalArgumentException("User_auth_DAO cannot be null");
        }
        this.userAuthDao = userAuthDao;
        this.loginHandler = new LoginHandler(userAuthDao);
        this.passwordHandler = new PasswordHandler(userAuthDao);
    }

    /**
     * Try to login and return User_info on success.
     */
    public User_info login(String username, String password) throws AuthServiceException {
        try {
            return loginHandler.login(username, password);
        } catch (LoginHandler.InvalidCredentialsException e) {
            throw new AuthServiceException(e.getMessage());
        }
    }


    /**
     * Simple exception that UI or API layer can catch.
     */
    public static class AuthServiceException extends Exception {
        public AuthServiceException(String message) {
            super(message);
        }
    }
}
