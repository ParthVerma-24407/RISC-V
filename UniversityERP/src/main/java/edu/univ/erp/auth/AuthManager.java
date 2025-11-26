package edu.univ.erp.auth;

import org.mindrot.jbcrypt.BCrypt;

/**
 * AuthManager
 *
 * - Provides methods to hash and verify passwords using BCrypt.
 * - Does NOT touch the database.
 *
 * Make sure jBCrypt jar is on your classpath.
 */
public final class AuthManager {

    private AuthManager() {
        // utility class
    }

    /**
     * Hash a plain-text password using BCrypt.
     *
     * @param plainPassword the plain-text password
     * @return the BCrypt hash to store in DB
     */
    public static String hashPassword(String plainPassword) {
        if (plainPassword == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(10));
    }

    /**
     * Verify a plain-text password against a stored BCrypt hash.
     *
     * @param plainPassword the password entered by user
     * @param storedHash    the hashed password from DB
     * @return true if match, false otherwise
     */
    public static boolean verifyPassword(String plainPassword, String storedHash) {
        if (plainPassword == null || storedHash == null) {
            return false;
        }
        try {
            return BCrypt.checkpw(plainPassword, storedHash);
        } catch (IllegalArgumentException e) {
            // in case storedHash is not a valid BCrypt hash
            return false;
        }
    }
}
