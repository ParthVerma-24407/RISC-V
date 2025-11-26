package edu.univ.erp.data.db;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ERPDBConnect {
    private static HikariDataSource erp_pool=null;
    public static void ERP_pool(){
        HikariConfig config =new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/erp_db");
        config.setUsername("root");
        config.setPassword("Parth2303@3110#");
        erp_pool=new HikariDataSource(config);

    }
    public static Connection getConnection_toErp() throws SQLException{
        return erp_pool.getConnection();
    }
}
