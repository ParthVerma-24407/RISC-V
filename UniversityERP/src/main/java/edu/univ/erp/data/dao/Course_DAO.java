package edu.univ.erp.data.dao;
import edu.univ.erp.domain.Course;
import edu.univ.erp.data.db.ERPDBConnect;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class Course_DAO {
    public boolean new_course_register(Course subject) throws SQLException {

        String query1 = "INSERT INTO courses (code, title, credits) VALUES (?,?,?)";
        try (Connection connection1 = ERPDBConnect.getConnection_toErp()) {
            try(PreparedStatement fetch = connection1.prepareStatement(query1)){
                fetch.setString(1, subject.getCode());
                fetch.setString(2, subject.getTitle());
                fetch.setInt(3, subject.getCredits());
                int course_fetch = fetch.executeUpdate();
                if (course_fetch > 0) {
                    System.out.println("this course has been added to the catalog. :)");
                    return true;
                }
                return false;
            }


        } catch (Exception e) {
            System.out.println("error has occured while adding the course.  :(");
            return false;
        }
    }
    public Course find_course_by_code(String code_id)throws SQLException{
        String query2="SELECT * FROM courses WHERE code = ? ";
        try(Connection connection2 = ERPDBConnect.getConnection_toErp()){
            try(PreparedStatement order2=connection2.prepareStatement(query2)){
                order2.setString(1,code_id);
                ResultSet table=order2.executeQuery();
                for(; table.next() ;){
                    String code=table.getString("code");
                    String title =table.getString("instructor_id");

                    int credits=table.getInt("room");
                    Course section2=new Course(code, title, credits);
                    return section2;
                }
            }
        } catch (Exception e) {
            System.out.println("Could not connect to database. :(");
        }
        return null;
    }
    public ArrayList<Course> Course_list() throws SQLException{
        ArrayList<Course> catalog = new ArrayList<>();
        String query2="SELECT * FROM courses";
        try(Connection conn2 = ERPDBConnect.getConnection_toErp()){
            try(PreparedStatement order2=conn2.prepareStatement(query2)){
                ResultSet table=order2.executeQuery();
                for(; table.next() ;){
                    String code =table.getString("code");
                    String title=table.getString("title");
                    int credits=table.getInt("credits");
                    Course course2=new Course(code,title,credits);
                    catalog.add(course2);
                }}
        } catch (Exception e) {
            System.out.println("Could not list the course due to Error. :(");
        }
        return catalog;
    }
    public boolean update_course(Course course) throws SQLException{
        try(Connection connection4=ERPDBConnect.getConnection_toErp()){
            String query4="UPDATE courses SET title = ?,credits=? WHERE code =?";
            try(PreparedStatement fetch=connection4.prepareStatement(query4)){
                fetch.setString(1, course.getTitle());
                fetch.setInt(2, course.getCredits());
                fetch.setString(3, course.getCode());

                int rows=fetch.executeUpdate();
                if (rows>0){
                    return true;
                }else {
                    return false;
                }

            }
        }catch (SQLException e){
            System.out.println("Could not update course. :(");
            return false;
        }
    }
    public boolean delete_bycode(String code) throws  SQLException {
        try (Connection connection3 = ERPDBConnect.getConnection_toErp()) {
            String query4 = "DELETE FROM courses WHERE code = ?";
            try (PreparedStatement order4 = connection3.prepareStatement(query4)) {
                order4.setString(1, code);
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

