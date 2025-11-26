package edu.univ.erp.data;
import edu.univ.erp.domain.Section;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Sections_APP {
    public boolean section(Section section) {
        try (Connection connection1 = DBmanager.getConnection_toErp()) {
            String query1 = "INSERT INTO sections (section_id, course_id,instructor_id,day_time, room,capacity, semester, year) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement fetch1 = connection1.prepareStatement(query1);
            fetch1.setInt(1, section.getSection_id());
            fetch1.setString(2, section.getCourse_id());
            fetch1.setInt(3,section.getInstructor_id());
            fetch1.setString(4, section.getDay_time());
            fetch1.setString(5, section.getRoom());
            fetch1.setInt(6, section.getCapacity());
            fetch1.setString(7, section.getSemester());
            fetch1.setInt(8, section.getYear());
            if (fetch1.executeUpdate()>0){
                return true;
            }
            else{
                return false;
            }
        } catch (Exception e) {
            System.out.println("error while adding section:");
            return false;
        }

    }public ArrayList<Section> Section_list(){
        ArrayList<Section> list = new ArrayList<>();
        String query2="SELECT * FROM sections";
        try(Connection conn2 = DBmanager.getConnection_toErp()){
            PreparedStatement order2=conn2.prepareStatement(query2);
            ResultSet table=order2.executeQuery();
            for(; table.next() ;){
                int section_id =table.getInt("section_id");
                String course_id=table.getString("course_id");
                int instructor_id=table.getInt("instructor_id");
                String day_time=table.getString("day_time");
                String room=table.getString("room");
                int capacity=table.getInt("capacity");
                String semester=table.getString("semester");
                int year=table.getInt("year");
                Section section2=new Section(section_id,course_id,instructor_id, day_time, room,capacity,semester,year);
                list.add(section2);
            }
        } catch (Exception e) {
            System.out.println("Could not list the Section. :(");
        }
        return list;
    }
}
