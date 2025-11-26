package edu.univ.erp.data;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBmanager {
    private static HikariDataSource auth_pool=null;
    private static HikariDataSource erp_pool=null;
    public static void ERP_pool(){
        HikariConfig config_2 =new HikariConfig();
        config_2.setJdbcUrl("jdbc:mysql://localhost:3306/erp_db");
        config_2.setUsername("root");
        config_2.setPassword("Parth2303@3110#");
        erp_pool=new HikariDataSource(config_2);
    }
    public static void Auth_pool(){
        HikariConfig config1 = new HikariConfig();
        config1.setJdbcUrl("jdbc:mysql://localhost:3306/auth_db");
        config1.setUsername("root");
        config1.setPassword("Parth2303@3110#");
        auth_pool = new HikariDataSource(config1);
    }
    public static Connection getConnection_toAuth() throws SQLException {
        return auth_pool.getConnection();
    }
    public static Connection getConnection_toErp() throws SQLException{
        return erp_pool.getConnection();
    }
}

