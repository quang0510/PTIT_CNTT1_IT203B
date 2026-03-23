package BaiTap.BaiTapVeNha.Bai3;

import java.lang.reflect.Type;
import java.sql.*;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bai3";
        String user = "root";
        String password = "Sp@tiz0510";

        Scanner scanner = new Scanner(System.in);

        try{
            Connection connection = DriverManager.getConnection(url , user , password);
            CallableStatement callableStatement = connection.prepareCall("{call GET_SURGERY_FEE (? , ?) }");

            callableStatement.setInt(1, 505);

            callableStatement.registerOutParameter(2, Types.DOUBLE);
            callableStatement.execute();

            double rs = callableStatement.getDouble(2);

            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            String formattedCost = nf.format(rs);

            System.out.printf("Chi phí phẫu thuật: %s\n", formattedCost);

            callableStatement.close();
            connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

/*
registerOutParameter() giúp JDBC:
Biết vị trí tham số OUT
Biết kiểu dữ liệu để convert

Nếu không đăng ký → lỗi:
column index is out of range
hoặc không lấy được dữ liệu
*/
