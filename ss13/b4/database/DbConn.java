package ss13.b4.database;
import java.sql.*;
import config.DatabaseConfig;

public class DbConn {
    public static Connection getC() throws Exception {
        Class.forName(DatabaseConfig.DRIVER);
        return DriverManager.getConnection(DatabaseConfig.getURL("Rikkei_Emergency"), DatabaseConfig.USER, DatabaseConfig.PASS);
    }
}
