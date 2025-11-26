package edu.univ.erp.data.dao;
import edu.univ.erp.domain.Grades;
import edu.univ.erp.data.db.ERPDBConnect;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class Grade_DAO {
    public boolean new_section(Grades grades) throws SQLException {
        try (Connection connection1 = ERPDBConnect.getConnection_toErp()) {
            String query1 = "INSERT INTO grades(enrollment_id, quiz_grade,mid_sem_grade, end_sem_grade, final_grade) VALUES (?,?,?,?,?)";
            try (PreparedStatement fetch1 = connection1.prepareStatement(query1)) {
                fetch1.setInt(1, grades.getEnrollment_id());
                fetch1.setDouble(2, grades.getQuizGrade());
                fetch1.setDouble(3, grades.getMid_Sem_grade());
                fetch1.setDouble(4, grades.getEnd_sem_grade());
                fetch1.setString(5, grades.getFinal_grade());
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
    public Grades findby_enrollment_id(int enrollment_id) throws SQLException {
        String query2 = "SELECT * FROM grades WHERE enrollment_id = ? ";
        try (Connection connection2 = ERPDBConnect.getConnection_toErp()) {
            try (PreparedStatement order2 = connection2.prepareStatement(query2)) {
                order2.setInt(1, enrollment_id);
                ResultSet table = order2.executeQuery();
                if (table.next()) {
                    int enrollmentId = table.getInt("enrollment_id");
                    Double student_id = table.getDouble("quiz_grade");
                    Double section_id = table.getDouble("mid_sem_grade");
                    Double status = table.getDouble("end_sem_grade");
                    String final_grade = table.getString("final_grade");
                    Grades grades = new Grades(enrollmentId, student_id, section_id, status, final_grade);
                    return grades;
                }

            } catch (Exception e) {
                System.out.println("Could not connect to database. :(");
            }
            return null;
        }
    }
    public boolean update_grades(Grades g) throws SQLException{
        try(Connection connection4=ERPDBConnect.getConnection_toErp()){
            String query4="UPDATE grades SET quiz_grade = ?,mid_sem_grade=?,end_sem_grade=?, final_grade=? WHERE enrollment_id =?";
            try(PreparedStatement fetch=connection4.prepareStatement(query4)){
                fetch.setInt(1, g.getEnrollment_id());
                fetch.setDouble(2, g.getQuizGrade());
                fetch.setDouble(3, g.getMid_Sem_grade());
                fetch.setDouble(4, g.getEnd_sem_grade());
                fetch.setString(5, g.getFinal_grade());
                int rows = fetch.executeUpdate();
                if (rows>0){
                    return true;
                }else {
                    return false;
                }

            }
        }catch (SQLException e){
            System.out.println("Could not update the Students enrolled. :(");
            return false;
        }
    }
    public boolean delete(int id) throws  SQLException {
        try (Connection connection3 = ERPDBConnect.getConnection_toErp()) {
            String query4 = "DELETE FROM grades WHERE enrollment_id = ?";
            try (PreparedStatement order4 = connection3.prepareStatement(query4)) {
                order4.setInt(1, id);
                int update = order4.executeUpdate();
                if (update > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException("connection error :(");
        }
    }
}

