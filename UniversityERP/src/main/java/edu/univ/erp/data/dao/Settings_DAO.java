package edu.univ.erp.data.dao;
import edu.univ.erp.data.db.ERPDBConnect;
import edu.univ.erp.domain.Grades;
import edu.univ.erp.domain.Settings;

import java.sql.*;

public class Settings_DAO {
    public Settings settings(String key) throws SQLException {
        try (Connection connection1 = ERPDBConnect.getConnection_toErp()) {
            String query1 = "SELECT * FROM settings WHERE setting_key = ? ";
            try (PreparedStatement fetch1 = connection1.prepareStatement(query1)) {
                fetch1.setString(1, key);
                ResultSet table = fetch1.executeQuery();

                if (table.next()) {
                    String key2 = table.getString("setting_key");
                    String value2 = table.getString("setting_value");
                    Settings settings = new Settings(key2, value2);
                    return settings;
                }
            }
        } catch (Exception e) {
            System.out.println("error while adding section:");

        }
        return null;
    }
    public boolean update(String key,String value) throws SQLException {
        try (Connection connection1 = ERPDBConnect.getConnection_toErp()) {
            String query1 = "UPDATE settings SET value=? WHERE key_name =?";
            try (PreparedStatement fetch1 = connection1.prepareStatement(query1)) {
                fetch1.setString(1, value);
                fetch1.setString(2, key);
                int rows = fetch1.executeUpdate();
                if (rows>0){
                    return true;
                }else {
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("error while adding section:");
            return false;
        }

    }


}
