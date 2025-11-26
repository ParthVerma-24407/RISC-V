package edu.univ.erp.access;

import edu.univ.erp.domain.User_info;

/**
 * AccessControl
 *
 * - Central place for permission checks.
 * - NO UI, NO DB here.
 *
 * Rules (simple version):
 *  - Student:
 *      - can view catalog, timetable, grades always
 *      - can register/drop only when NOT in maintenance
 *  - Instructor:
 *      - can view their sections always
 *      - can edit grades only when NOT in maintenance
 *  - Admin:
 *      - can always manage users/courses/sections
 *      - can toggle maintenance
 */
public final class AccessControl {

    private AccessControl() {
        // utility class
    }

    // -------- Student permissions -------- //

    /**
     * Student can view catalog even during maintenance.
     */
    public static boolean canStudentViewCatalog(User_info user) {
        return RoleManager.isStudent(user);
    }

    /**
     * Student can register for sections only if NOT in maintenance.
     */
    public static boolean canStudentRegister(User_info user, boolean maintenanceMode) {
        return RoleManager.isStudent(user) && !maintenanceMode;
    }

    /**
     * Student can drop sections only if NOT in maintenance.
     */
    public static boolean canStudentDrop(User_info user, boolean maintenanceMode) {
        return RoleManager.isStudent(user) && !maintenanceMode;
    }

    /**
     * Student can view timetable any time.
     */
    public static boolean canStudentViewTimetable(User_info user) {
        return RoleManager.isStudent(user);
    }

    /**
     * Student can view grades any time.
     */
    public static boolean canStudentViewGrades(User_info user) {
        return RoleManager.isStudent(user);
    }

    // -------- Instructor permissions -------- //

    /**
     * Instructor can see their sections always.
     */
    public static boolean canInstructorViewSections(User_info user) {
        return RoleManager.isInstructor(user);
    }

    /**
     * Instructor can update scores only if NOT in maintenance.
     */
    public static boolean canInstructorEditGrades(User_info user, boolean maintenanceMode) {
        return RoleManager.isInstructor(user) && !maintenanceMode;
    }

    /**
     * Instructor can view stats any time.
     */
    public static boolean canInstructorViewStats(User_info user) {
        return RoleManager.isInstructor(user);
    }

    // -------- Admin permissions -------- //

    public static boolean canAdminManageUsers(User_info user) {
        return RoleManager.isAdmin(user);
    }

    public static boolean canAdminManageCourses(User_info user) {
        return RoleManager.isAdmin(user);
    }

    public static boolean canAdminManageSections(User_info user) {
        return RoleManager.isAdmin(user);
    }

    public static boolean canAdminToggleMaintenance(User_info user) {
        return RoleManager.isAdmin(user);
    }
}
