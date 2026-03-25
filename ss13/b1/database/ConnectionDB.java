package ss13.b1.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import config.DatabaseConfig;

public class ConnectionDB {
    public static Connection getConn() throws SQLException, ClassNotFoundException {
        Class.forName(DatabaseConfig.DRIVER);
        Connection c = DriverManager.getConnection(DatabaseConfig.getURL("RikkeiHospital_DB"), DatabaseConfig.USER, DatabaseConfig.PASS);
        return c;
    }
}