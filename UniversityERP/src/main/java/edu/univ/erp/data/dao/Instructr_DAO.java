package edu.univ.erp.data.dao;
import edu.univ.erp.domain.Course;
import edu.univ.erp.domain.Instructor;
import edu.univ.erp.data.db.ERPDBConnect;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Instructr_DAO {
    public boolean new_instructor(Instructor instructor, String name) throws SQLException {

        String query1 = "INSERT INTO instructors (name, department) VALUES (?,?)";
        try (Connection connection1 = ERPDBConnect.getConnection_toErp()) {
            try (PreparedStatement fetch = connection1.prepareStatement(query1)) {
                fetch.setString(1, name);
                fetch.setString(2, instructor.getDepartment());
                int course_fetch = fetch.executeUpdate();
                if (course_fetch > 0) {
                    System.out.println("this course has been added to the catalog. :)");
                    return true;
                }
                return false;
            }
        }
        }
    public Instructor find_course_by_code(int id)throws SQLException{
        String query2="SELECT * FROM instructors WHERE instructor_id = ? ";
        try(Connection connection2 = ERPDBConnect.getConnection_toErp()){
            try(PreparedStatement order2=connection2.prepareStatement(query2)){
                order2.setInt(1,id);
                ResultSet table=order2.executeQuery();
                if(table.next()){
                    int id1=table.getInt("instructor_id");
                    String dept =table.getString("department");

                    Instructor instructor_upd=new Instructor(id1, dept);
                    return instructor_upd;
                }
            }
        } catch (Exception e) {
            System.out.println("Could not connect to database. :(");
        }
        return null;
    }
}


