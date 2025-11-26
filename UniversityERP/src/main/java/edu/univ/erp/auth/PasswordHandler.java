package edu.univ.erp.auth;

import edu.univ.erp.data.dao.User_auth_DAO;
import edu.univ.erp.domain.User_info;

import java.sql.SQLException;

/**
 * PasswordHandler
 *
 * - Handles password change for a user.
 * - Checks old password using AuthManager.
 * - Hashes new password and updates via User_auth_DAO.update_user_info().
 */
public class PasswordHandler {

    private final User_auth_DAO userAuthDao;

    public PasswordHandler(User_auth_DAO userAuthDao) {
        if (userAuthDao == null) {
            throw new IllegalArgumentException("User_auth_DAO cannot be null");
        }
        this.userAuthDao = userAuthDao;
    }


    /**
     * Exception for password change failures.
     */
    public static class PasswordChangeException extends Exception {
        public PasswordChangeException(String message) {
            super(message);
        }
    }
}
