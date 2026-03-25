package ss13.tonghop.database;
import java.sql.*;
import config.DatabaseConfig;

public class DbConn {
    public static Connection getC() throws Exception {
        Class.forName(DatabaseConfig.DRIVER);
        return DriverManager.getConnection(DatabaseConfig.getURL("Hospital_DB2"), DatabaseConfig.USER, DatabaseConfig.PASS);
    }
}
