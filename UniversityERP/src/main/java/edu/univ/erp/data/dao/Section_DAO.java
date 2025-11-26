package edu.univ.erp.data.dao;
import edu.univ.erp.domain.Section;
import edu.univ.erp.data.db.ERPDBConnect;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class Section_DAO {
    public boolean new_section(Section section)throws SQLException {
        try (Connection connection1 = ERPDBConnect.getConnection_toErp()) {
            String query1 = "INSERT INTO sections (section_id, course_id,instructor_id,day_time, room,capacity, semester, year) VALUES (?,?,?,?,?,?,?,?)";
            try(PreparedStatement fetch1 = connection1.prepareStatement(query1)){
                fetch1.setInt(1, section.getSection_id());
                fetch1.setString(2, section.getCourse_id());
                fetch1.setInt(3,section.getInstructor_id());
                fetch1.setString(4, section.getDay_time());
                fetch1.setString(5, section.getRoom());
                fetch1.setInt(6, section.getCapacity());
                fetch1.setString(7, section.getSemester());
                fetch1.setInt(8, section.getYear());
                int rows=fetch1.executeUpdate();
                if (rows>0){
                    return true;
                }
                else{
                    return false;
                }}
        } catch (Exception e) {
            System.out.println("error while adding section:");
            return false;
        }

    }
    public Section findby_section_id(int section_id)throws SQLException{
        String query2="SELECT section_id, course_id, instructor_id, day-time, room, capacity, semester, year FROM sections WHERE section_id = ? ";
        try(Connection connection2 = ERPDBConnect.getConnection_toErp()){
            try(PreparedStatement order2=connection2.prepareStatement(query2)){
                order2.setInt(1,section_id);
                ResultSet table=order2.executeQuery();
                for(; table.next() ;){
                    int sec_id =table.getInt("section_id");
                    String course_id=table.getString("course_id");
                    int instructor_id=table.getInt("instructor_id");
                    String day_time=table.getString("day_time");
                    String room=table.getString("room");
                    int capacity=table.getInt("capacity");
                    String semester=table.getString("semester");
                    int year=table.getInt("year");
                    Section section2=new Section(sec_id,course_id,instructor_id, day_time, room,capacity,semester,year);
                    section2.setEnrolled(table.getInt("ENROLLED"));
                    return section2;
                }
            }
        } catch (Exception e) {
            System.out.println("Could not connect to database. :(");
        }
        return null;
    }
    public List<Section> all_sections() throws  SQLException{
        String query3="SELECT * FROM sections";
        ArrayList<Section> sections_list= new ArrayList<>();
        try(Connection connection3= ERPDBConnect.getConnection_toErp()){
            try(PreparedStatement order3=connection3.prepareStatement(query3)){
                try(ResultSet table=order3.executeQuery()){
                    for(;table.next();){
                        int section_id=table.getInt("section_id");
                        String course_id=table.getString("course_id");
                        int instructor_id=table.getInt("instructor_id");
                        String day_time = table.getString("day_time");
                        String room=table.getString("room");
                        int capacity=table.getInt("capacity");
                        String semester=table.getString("semester");
                        int year=table.getInt("year");
                        Section section=new Section(section_id, course_id, instructor_id, day_time, room,capacity, semester,year);
                        section.setEnrolled(table.getInt("enrolled"));
                        sections_list.add(section);

                    }}
            }catch (SQLException e){
                System.out.println("Could not get all the Section. :(");
            }
            return sections_list;
        }
    }
    public boolean update_students_no(int section_id, int count_updated) throws SQLException{
        try(Connection connection4=ERPDBConnect.getConnection_toErp()){
            String query4="UPDATE sections SET enrolled = ? WHERE section_id =?";
            try(PreparedStatement fetch=connection4.prepareStatement(query4)){
                fetch.setInt(1,count_updated);
                fetch.setInt(2,section_id);
                int rows=fetch.executeUpdate();
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
    public boolean terminate_section(int section_id) throws SQLException{
        try(Connection connection5=ERPDBConnect.getConnection_toErp()){
            String query5="DELETE FROM sections WHERE section_id=?";
            try(PreparedStatement fetch=connection5.prepareStatement(query5)){
                fetch.setInt(1,section_id);
                int rows=fetch.executeUpdate();
                if (rows>0){
                    return true;
                }else {
                    return false;
                }
            }
        }catch (SQLException e){
            System.out.println("Could not delete the section. :(");
            return false;
        }
    }
    public boolean updatedetails_section(Section section) throws SQLException{
        try(Connection connection5= ERPDBConnect.getConnection_toErp()){
            String query5= "UPDATE students SET course_id = ?, instructor_id=?, day_time=?, capacity=?, semester=?,year = ?, enrolled=? WHERE section_id =?";
            try (PreparedStatement order5 =connection5.prepareStatement(query5)){
                order5.setString(1,section.getCourse_id());
                order5.setInt(2,section.getInstructor_id());
                order5.setString(3,section.getDay_time());
                order5.setInt(4,section.getCapacity());
                order5.setString(5,section.getSemester());
                order5.setInt(6,section.getYear());
                order5.setInt(7,section.getEnrolled());
                order5.setInt(8,section.getSection_id());

                int rows= order5.executeUpdate();
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
