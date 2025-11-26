package edu.univ.erp.data;
import edu.univ.erp.domain.Enrollments;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
public class Enrollment_menu {
    public boolean check_duplicate(int student_id, int section_id){
        boolean has_duplicate=false;
        String query1 = "SELECT * FROM enrollments WHERE student_id =? AND section_id=?";
        try(Connection connection1 =DBmanager.getConnection_toErp()){
            PreparedStatement fetch1= connection1.prepareStatement(query1);
            fetch1.setInt(1,student_id);
            fetch1.setInt(2,section_id);
            ResultSet list1= fetch1.executeQuery();
            if (list1.next()){
                has_duplicate=true;
            }
        }catch(Exception e){
            System.out.println("Duplicate cannot be recognised for now.");
        }
        return has_duplicate;
    }
    public void save_enrollment(Enrollments enrollments){
        String query2="INSERT INTO enrollments (enrollment_id,student_id,section_id, status) VALUES (?,?,?)";
        try(Connection connection2 =DBmanager.getConnection_toErp()){
            PreparedStatement fetch1=connection2.prepareStatement(query2);
            fetch1.setInt(1,enrollments.getEnrollment_id());
            fetch1.setInt(2, enrollments.getStudent_id());
            fetch1.setInt(3, enrollments.getSection_id());
            fetch1.setString(4,enrollments.getStatus());
            fetch1.executeUpdate();
            System.out.println("Enrollment has been saved.");
        }catch(Exception e){
            System.out.println("cannot save enrollment.");
        }
    }
    public void delete_enrollment(int student_id, int section_id){
        String query3="UPDATE enrollments SET status='Dropped' WHERE student_id=? AND section_id=?";
        try(Connection connection3 =DBmanager.getConnection_toErp()){
            PreparedStatement fetch1=connection3.prepareStatement(query3);
            fetch1.setInt(1,student_id);
            fetch1.setInt(2,section_id);
            fetch1.executeUpdate();
            System.out.println("Enrollement marked as dropped.");
        }catch(Exception e){
            System.out.println("Could not delete enrollment.");
        }
    }
    public ArrayList<String> get_grades(int student_id){
        ArrayList<String> List=new ArrayList<>();
        String query4="SELECT c.title, g.component,g.score, g.final_grade FROM grades g JOIN enrollments e ON g.enrollment_id=e.enrollment_id JOIN sections s ON e.section_id=s.section_id JOIN courses c ON s.course_id=c.code WHERE e.student_id = ?";
        try(Connection connection4=DBmanager.getConnection_toErp()){
            PreparedStatement fetch=connection4.prepareStatement(query4);
            fetch.setInt(1,student_id);
            ResultSet table= fetch.executeQuery();
            for(; table.next() ;){
                String title=table.getString("title");
                String component=table.getString("component");
                double score=table.getDouble("score");
                String final_grade=table.getString("final_grade");

                List.add(title + " ; " + component + "=" + score +"("+final_grade+")");
            }
        }catch(Exception e){
            System.out.println("cannot show grades.");
        }
        return List;
    }
}
