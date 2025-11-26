package edu.univ.erp.data.dao;
import edu.univ.erp.domain.Enrollments;
import edu.univ.erp.domain.Student;
import edu.univ.erp.domain.User_info;
import edu.univ.erp.data.db.ERPDBConnect;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class User_auth_DAO {
    public boolean add_user(User_info user) throws SQLException {
        try (Connection connection1 = ERPDBConnect.getConnection_toErp()) {
            String query1 = "INSERT INTO users_auth(username, role,password_hash, status,last_login, prev_create, updated_at) VALUES (?,?,?,?,?,?,?)";
            try (PreparedStatement fetch1 = connection1.prepareStatement(query1)) {
                fetch1.setString(1, user.getUsername());
                fetch1.setString(2, user.getRole());
                fetch1.setString(3, user.getPassword_hash());
                fetch1.setString(4, user.getStatus());
                fetch1.setString(5, user.getLastLogin());
                fetch1.setString(6, user.getPrevCreate());
                fetch1.setString(7, user.getUpdatedAt());

                int rows = fetch1.executeUpdate();
                if (rows > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("database connection error");
            return false;
        }
    }
    public User_info findby_username(String username) throws SQLException {
        String query2 = "SELECT * FROM users_auth WHERE username = ? ";
        try (Connection connection2 = ERPDBConnect.getConnection_toErp()) {
            try (PreparedStatement order2 = connection2.prepareStatement(query2)) {
                order2.setString(1, username);
                ResultSet table = order2.executeQuery();
                if (table.next()) {
                    String usern = table.getString("username");
                    String role = table.getString("role");
                    String password = table.getString("password_hash");
                    String status = table.getString("status");
                    String last_login = table.getString("last_login");
                    String pre_created = table.getString("prev_create");
                    String updated_at = table.getString("updated_at");

                    User_info user = new User_info(usern, role, password,status,last_login,pre_created,updated_at);
                    return user;
                }
            }
        } catch (Exception e) {
            System.out.println("Could not connect to database. :(");
        }
        return null;
    }
    public ArrayList<User_info> all_users() throws SQLException {
        String query3 = "SELECT * FROM users_auth";
        ArrayList<User_info> User_list = new ArrayList<>();
        try (Connection connection3 = ERPDBConnect.getConnection_toErp()) {
            try (PreparedStatement order3 = connection3.prepareStatement(query3)) {
                try (ResultSet table = order3.executeQuery()) {
                    for (; table.next(); ) {
                        String usern = table.getString("username");
                        String role = table.getString("role");
                        String password = table.getString("password_hash");
                        String status = table.getString("status");
                        String last_login = table.getString("last_login");
                        String pre_created = table.getString("prev_create");
                        String updated_at = table.getString("updated_at");

                        User_info user = new User_info(usern, role, password,status,last_login,pre_created,updated_at);
                        User_list.add(user);

                    }
                }
            } catch (SQLException e) {
                System.out.println("Could not get all the Section. :(");
            }
            return User_list;
        }
    }
    public boolean update_user_info(User_info user) throws  SQLException{
        try(Connection connection3= ERPDBConnect.getConnection_toErp()){
            String query3= "UPDATE users_auth SET role = ?, password_hash = ?, status=?, last_login = ?,prev_create=?,updated_at=? WHERE username =?";
            try (PreparedStatement order3 =connection3.prepareStatement(query3)){
                order3.setString(1,user.getRole());
                order3.setString(2,user.getPassword_hash());
                order3.setString(3,user.getStatus());
                order3.setString(4,user.getLastLogin());
                order3.setString(5,user.getPrevCreate());
                order3.setString(6,user.getUpdatedAt());
                order3.setString(7,user.getUsername());

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
    public boolean delete_byusername(String username) throws  SQLException {
        try (Connection connection3 = ERPDBConnect.getConnection_toErp()) {
            String query4 = "DELETE FROM users_auth WHERE username = ?";
            try (PreparedStatement order4 = connection3.prepareStatement(query4)) {
                order4.setString(1, username);
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
