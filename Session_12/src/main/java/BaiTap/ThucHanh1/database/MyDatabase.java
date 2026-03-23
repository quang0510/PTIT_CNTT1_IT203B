package BaiTap.ThucHanh1.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyDatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/k24_java_advanced_sessioin12?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASSWORD = "Sp@tiz0510";
    public static Connection getConnection(){
        try {

            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Kết nối thành công");
            return connection;
        }catch (Exception e){
            System.out.println("Kết nối thất bại : " + e.getMessage());

        }
        return null;
    }

    public static void main(String[] args) {
        Connection connection = getConnection();
    }
}
