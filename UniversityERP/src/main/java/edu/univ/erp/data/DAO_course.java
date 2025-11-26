package edu.univ.erp.data.dao;
import edu.univ.erp.domain.Course;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO_course {
    public boolean new_course_register(Course subject) {

        String query1 = "INSERT INTO courses (code, title, credits) VALUES (?,?,?)";
        try (Connection connection1 = DBmanager.getConnection_toErp()) {
            PreparedStatement fetch = connection1.prepareStatement(query1);
            fetch.setString(1, subject.getCode());
            fetch.setString(2, subject.getTitle());
            fetch.setInt(3, subject.getCredits());
            int course_fetch = fetch.executeUpdate();
            if (course_fetch > 0) {
                System.out.println("this course has been added to the catalog. :)");
                return true;
            }
            return false;


        } catch (Exception e) {
            System.out.println("error has occured while adding the course.  :(");
            return false;
        }
    }
    public ArrayList<Course> Course_list(){
        ArrayList<Course> catalog = new ArrayList<>();
        String query2="SELECT * FROM courses";
        try(Connection conn2 = DBmanager.getConnection_toErp()){
            PreparedStatement order2=conn2.prepareStatement(query2);
            ResultSet table=order2.executeQuery();
            for(; table.next() ;){
                String code =table.getString("code");
                String title=table.getString("title");
                int credits=table.getInt("credits");
                Course course2=new Course(code,title,credits);
                catalog.add(course2);
            }
        } catch (Exception e) {
            System.out.println("Could not list the course due to Error. :(");
        }
        return catalog;
    }
}

