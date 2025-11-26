package edu.univ.erp.service;

import edu.univ.erp.data.dao.Student_DAO;
import edu.univ.erp.data.dao.Section_DAO;
import edu.univ.erp.data.dao.Enrollment_DAO;
import edu.univ.erp.data.dao.Grade_DAO;
import edu.univ.erp.domain.Student;
import edu.univ.erp.domain.Section;
import edu.univ.erp.domain.Enrollments;
import edu.univ.erp.domain.Grades;

import java.sql.SQLException;
import java.util.List;

/**
 * StudentService
 *
 * Business logic for student operations:
 * - view catalog
 * - register section
 * - drop section
 * - view timetable
 * - view grades
 *
 * NO bonus features (no transcript export here).
 */
public class StudentService {

    private final Student_DAO studentDao;
    private final SectionDao sectionDao;
    private final EnrollmentDao enrollmentDao;
    private final GradeDao gradeDao;

    public StudentService(Student_DAO studentDao,
                          SectionDao sectionDao,
                          EnrollmentDao enrollmentDao,
                          GradeDao gradeDao) {
        this.studentDao = studentDao;
        this.sectionDao = sectionDao;
        this.enrollmentDao = enrollmentDao;
        this.gradeDao = gradeDao;
    }

    /**
     * Get catalog of all sections open for registration.
     * Implement findAllOpenSections() in SectionDao.
     */
    public List<Section> getCourseCatalog() throws StudentServiceException {
        try {
            return sectionDao.findAllOpenSections();
        } catch (SQLException e) {
            throw new StudentServiceException("Unable to fetch course catalog.");
        }
    }

    /**
     * Register the logged-in student (by username) into a section.
     */
    public void registerSection(String username, int sectionId) throws StudentServiceException {
        try {
            // 1. Find student by username
            Student student = studentDao.findByUsername(username);
            if (student == null) {
                throw new StudentServiceException("Student not found.");
            }

            // Example: assuming Student has getRollno()
            String rollNo = student.getRollno();

            // 2. Check if already enrolled
            boolean already = enrollmentDao.exists(rollNo, sectionId);
            if (already) {
                throw new StudentServiceException("Already registered in this section.");
            }

            // 3. Check section capacity
            Section section = sectionDao.findById(sectionId);
            if (section == null) {
                throw new StudentServiceException("Section not found.");
            }
            if (section.getCurrentCapacity() >= section.getMaxCapacity()) {
                throw new StudentServiceException("Section is full.");
            }

            // 4. Enroll student
            enrollmentDao.addEnrollment(rollNo, sectionId);

            // 5. Update section capacity
            sectionDao.incrementCurrentCapacity(sectionId);

        } catch (SQLException e) {
            throw new StudentServiceException("Error while registering for section.");
        }
    }

    /**
     * Drop a section for a student.
     */
    public void dropSection(String username, int sectionId) throws StudentServiceException {
        try {
            Student student = studentDao.findByUsername(username);
            if (student == null) {
                throw new StudentServiceException("Student not found.");
            }
            String rollNo = student.getRollno();

            boolean exists = enrollmentDao.exists(rollNo, sectionId);
            if (!exists) {
                throw new StudentServiceException("You are not registered in this section.");
            }

            // Remove enrollment
            enrollmentDao.deleteEnrollment(rollNo, sectionId);

            // Update capacity
            sectionDao.decrementCurrentCapacity(sectionId);

        } catch (SQLException e) {
            throw new StudentServiceException("Error while dropping section.");
        }
    }

    /**
     * Get timetable sections for a student.
     */
    public List<Section> getTimetable(String username) throws StudentServiceException {
        try {
            Student student = studentDao.findByUsername(username);
            if (student == null) {
                throw new StudentServiceException("Student not found.");
            }
            String rollNo = student.getRollno();
            return sectionDao.findSectionsByStudent(rollNo);
        } catch (SQLException e) {
            throw new StudentServiceException("Unable to fetch timetable.");
        }
    }

    /**
     * Get all grades for a student.
     */
    public List<Grades> getGrades(String username) throws StudentServiceException {
        try {
            Student student = studentDao.findByUsername(username);
            if (student == null) {
                throw new StudentServiceException("Student not found.");
            }
            String rollNo = student.getRoll_no();
            return gradeDao.findByStudentRoll(rollNo);
        } catch (SQLException e) {
            throw new StudentServiceException("Unable to fetch grades.");
        }
    }

    public static class StudentServiceException extends Exception {
        public StudentServiceException(String message) {
            super(message);
        }
    }
}
