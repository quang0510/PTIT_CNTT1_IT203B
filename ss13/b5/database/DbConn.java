package ss13.b5.database;
import java.sql.*;
import config.DatabaseConfig;

public class DbConn {
    public static Connection getC() throws Exception {
        Class.forName(DatabaseConfig.DRIVER);
        return DriverManager.getConnection(DatabaseConfig.getURL("Rikkei_Reception"), DatabaseConfig.USER, DatabaseConfig.PASS);
    }
}
