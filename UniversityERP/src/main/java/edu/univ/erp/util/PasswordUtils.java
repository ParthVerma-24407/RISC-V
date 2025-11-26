package edu.univ.erp.util;

import org.mindrot.jbcrypt.BCrypt;

public final class PasswordUtils {

    private static final int WORK_FACTOR = 10;

    private PasswordUtils() {
        // utility class
    }

    public static String hashPassword(String plainPassword) {
        if (plainPassword == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(WORK_FACTOR));
    }

    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        if (plainPassword == null || hashedPassword == null) {
            return false;
        }
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
