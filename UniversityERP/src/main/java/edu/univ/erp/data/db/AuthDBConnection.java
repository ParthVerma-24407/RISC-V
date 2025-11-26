package edu.univ.erp.data.db;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class AuthDBConnection {
    private static HikariDataSource auth_pool=null;
    public static void Auth_pool(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/auth_db");
        config.setUsername("root");
        config.setPassword("Parth2303@3110#");
        auth_pool = new HikariDataSource(config);
    }
    public static Connection getConnection_toAuth() throws SQLException {
        return auth_pool.getConnection();
    }
}