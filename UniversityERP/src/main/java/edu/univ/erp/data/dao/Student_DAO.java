package edu.univ.erp.data.dao;
import edu.univ.erp.domain.Student;
import edu.univ.erp.data.db.ERPDBConnect;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Student_DAO {
    public void new_student_register(Student student) throws SQLException{
        if (student==null){
            System.out.println("no course is to be provided now!!!");
            return;
        }
        String query1 = "INSERT INTO students (roll_no, program,year) VALUES (?,?,?)";
        try (Connection connection1 = ERPDBConnect.getConnection_toErp()) {
            try (PreparedStatement fetch = connection1.prepareStatement(query1)) {
                fetch.setString(1, student.getRoll_no());
                fetch.setString(2, student.getProgram());
                fetch.setInt(3, student.getYear());
                int student_fetch=fetch.executeUpdate();
                if(student_fetch>0) {
                    System.out.println("Student has been registered :)");
                } else{
                    System.out.println(" no student was added!!");
                }

            }
        } catch (Exception e) {
            System.out.println("error occured while saving student :(");
        }
    }
    public Student search_by_rollno(int roll_no) throws  SQLException{
        try(Connection connection2= ERPDBConnect.getConnection_toErp()){
            String query2="roll_no, program,year FROM students WHERE roll_no = ?";
            try (PreparedStatement order2 =connection2.prepareStatement(query2)){
                order2.setInt(1,roll_no);
                ResultSet search=order2.executeQuery();
                if (search.next()){
                    String Roll_no=search.getString("roll_no");
                    String program=search.getString("program");
                    int year=search.getInt("year");
                    Student student=new Student(Roll_no,program,year);
                    return student;
                }
            }
        }catch (SQLException e){
            throw new RuntimeException("SORRY!!! but there is no such student. :(");
        }
        return null;
    }
    public boolean update_student_info(Student student) throws  SQLException{
        try(Connection connection3= ERPDBConnect.getConnection_toErp()){
            String query3= "UPDATE students SET program = ?, year = ? WHERE roll_no =?";
            try (PreparedStatement order3 =connection3.prepareStatement(query3)){
                order3.setString(1,student.getProgram());
                order3.setInt(2,student.getYear());
                order3.setString(3,student.getRoll_no());
                int rows= order3.executeUpdate();
                if (rows>0){
                    return true;
                }else{
                    return false;
                }
            }catch(SQLException e){
                throw new RuntimeException("SORRY!!! but this can't be updated :(");
            }
        }

    }
    public boolean delete_byrollno(String roll_no) throws  SQLException {
        try (Connection connection3 = ERPDBConnect.getConnection_toErp()) {
            String query4 = "DELETE FROM students WHERE roll_no = ?";
            try (PreparedStatement order4 = connection3.prepareStatement(query4)) {
                order4.setString(1, roll_no);
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

