package edu.univ.erp.data.dao;
import edu.univ.erp.domain.Enrollments;
import edu.univ.erp.data.db.ERPDBConnect;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Enrollment_DAO {
    public boolean new_section(Enrollments enrollment) throws SQLException {
        try (Connection connection1 = ERPDBConnect.getConnection_toErp()) {
            String query1 = "INSERT INTO enrollments (enrollment_id, student_id,section_id, status) VALUES (?,?,?,?)";
            try (PreparedStatement fetch1 = connection1.prepareStatement(query1)) {
                fetch1.setInt(1, enrollment.getEnrollment_id());
                fetch1.setInt(2, enrollment.getStudent_id());
                fetch1.setInt(3, enrollment.getSection_id());
                fetch1.setString(4, enrollment.getStatus());
                int rows = fetch1.executeUpdate();
                if (rows > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("error while adding section:");
            return false;
        }
    }

    public Enrollments findby_enrollment_id(int enrollment_id) throws SQLException {
        String query2 = "SELECT * FROM enrollments WHERE enrollment_id = ? ";
        try (Connection connection2 = ERPDBConnect.getConnection_toErp()) {
            try (PreparedStatement order2 = connection2.prepareStatement(query2)) {
                order2.setInt(1, enrollment_id);
                ResultSet table = order2.executeQuery();
                if (table.next()) {
                    int sec_id = table.getInt("enrollment_id");
                    int student_id = table.getInt("student_id");
                    int section_id = table.getInt("section_id");
                    String status = table.getString("status");

                    Enrollments enrollments = new Enrollments(sec_id, student_id, section_id, status);
                    return enrollments;
                }
            }
        } catch (Exception e) {
            System.out.println("Could not connect to database. :(");
        }
        return null;
    }

    public List<Enrollments> all_enrollments(int student_id) throws SQLException {
        String query3 = "SELECT * FROM enrollments WHERE student_id = ?";
        ArrayList<Enrollments> enrollments_list = new ArrayList<>();
        try (Connection connection3 = ERPDBConnect.getConnection_toErp()) {
            try (PreparedStatement order3 = connection3.prepareStatement(query3)) {
                try (ResultSet table = order3.executeQuery()) {
                    order3.setInt(1,student_id);
                    for (; table.next(); ) {
                        int enrollment = table.getInt("section_id");
                        int stud_id = table.getInt("instructor_id");
                        int section_id = table.getInt("capacity");
                        String status = table.getString("day_time");
                        Enrollments enrollments = new Enrollments(enrollment, stud_id, section_id, status);
                        enrollments_list.add(enrollments);

                    }
                }
            } catch (SQLException e) {
                System.out.println("Could not get all the Section. :(");
            }
            return enrollments_list;

        }
    }
    public boolean checking(int student_id, int section_id) throws SQLException {
        try (Connection connection1 = ERPDBConnect.getConnection_toErp()) {
            String query1 = "SELECT * FROM enrollments WHERE student_id = ? and section_id = ?";
            try (PreparedStatement fetch1 = connection1.prepareStatement(query1)) {
                fetch1.setInt(1, student_id);
                fetch1.setInt(2, section_id);
                int rows = fetch1.executeUpdate();
                if (rows > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("error while checking enrollments:");
            return false;
        }
    }
    public boolean delete(int id) throws  SQLException {
        try (Connection connection3 = ERPDBConnect.getConnection_toErp()) {
            String query4 = "DELETE FROM enrollments WHERE enrollment_id = ?";
            try (PreparedStatement order4 = connection3.prepareStatement(query4)) {
                order4.setInt(1, id);
                int update = order4.executeUpdate();
                if (update > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                throw new RuntimeException("SORRY!!! but this can't be updated :(");
            }
        }
    }
}