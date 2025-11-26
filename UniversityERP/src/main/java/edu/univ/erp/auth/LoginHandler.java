package edu.univ.erp.auth;

import edu.univ.erp.data.dao.User_auth_DAO;
import edu.univ.erp.domain.User_info;

import java.sql.SQLException;

/**
 * LoginHandler
 *
 * - Talks to User_auth_DAO to fetch user by username.
 * - Uses AuthManager to verify password.
 * - No UI or Swing code here.
 */
public class LoginHandler {

    private final User_auth_DAO userAuthDao;

    public LoginHandler(User_auth_DAO userAuthDao) {
        if (userAuthDao == null) {
            throw new IllegalArgumentException("User_auth_DAO cannot be null");
        }
        this.userAuthDao = userAuthDao;
    }

    /**
     * Try to log in with given username and password.
     *
     * @param username      username entered
     * @param plainPassword password entered
     * @return User_info if login is successful
     * @throws InvalidCredentialsException if username not found or password incorrect
     */
    public User_info login(String username, String plainPassword) throws InvalidCredentialsException {
        if (username == null || username.isBlank()) {
            throw new InvalidCredentialsException("Username cannot be empty.");
        }
        if (plainPassword == null || plainPassword.isBlank()) {
            throw new InvalidCredentialsException("Password cannot be empty.");
        }

        User_info user;
        try {
            user = userAuthDao.findby_username(username);
        } catch (SQLException e) {
            // Hide SQL details from caller
            throw new InvalidCredentialsException("Unable to login due to a system error.");
        }

        if (user == null) {
            // No such username
            throw new InvalidCredentialsException("Invalid username or password.");
        }

        String storedHash = user.getPassword_hash();
        boolean ok = AuthManager.verifyPassword(plainPassword, storedHash);

        if (!ok) {
            throw new InvalidCredentialsException("Invalid username or password.");
        }

        // Login successful
        return user;
    }

    /**
     * Simple custom exception for invalid login attempts.
     */
    public static class InvalidCredentialsException extends Exception {
        public InvalidCredentialsException(String message) {
            super(message);
        }
    }
}
