package edu.univ.erp.access;

import edu.univ.erp.domain.User_info;

/**
 * RoleManager
 *
 * - Central place to interpret user roles.
 * - Works only with User_info (no DB here).
 */
public final class RoleManager {

    public static final String ROLE_STUDENT    = "STUDENT";
    public static final String ROLE_INSTRUCTOR = "INSTRUCTOR";
    public static final String ROLE_ADMIN      = "ADMIN";

    private RoleManager() {
        // utility class
    }

    /**
     * Get the raw role string from user (uppercase trimmed).
     */
    public static String getRole(User_info user) {
        if (user == null || user.getRole() == null) {
            return null;
        }
        return user.getRole().trim().toUpperCase();
    }

    public static boolean isStudent(User_info user) {
        return ROLE_STUDENT.equals(getRole(user));
    }

    public static boolean isInstructor(User_info user) {
        return ROLE_INSTRUCTOR.equals(getRole(user));
    }

    public static boolean isAdmin(User_info user) {
        return ROLE_ADMIN.equals(getRole(user));
    }
}
