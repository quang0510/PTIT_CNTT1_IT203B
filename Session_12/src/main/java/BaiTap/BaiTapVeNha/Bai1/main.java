package BaiTap.BaiTapVeNha.Bai1;

import java.sql.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bai1";
        String user = "root";
        String password = "Sp@tiz0510";

        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập code: ");
        String code = scanner.nextLine();

        System.out.print("Nhập pass: ");
        String pass = scanner.nextLine();

        try{
            Connection connection = DriverManager.getConnection(url , user , password);

            String sql = "select * from Doctors where code = ? and pass = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, code);
            preparedStatement.setString(2, pass);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                System.out.println("Đăng nhập thành công");
            } else {
                System.out.println("Sai tài khoản hoặc mật khẩu");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
/*
PreparedStatement tách câu lệnh SQL và dữ liệu
Ngăn chặn việc chèn mã độc vào SQL
Cơ chế pre-compiled giúp:
Tăng hiệu năng (chạy lại nhiều lần)
Đảm bảo dữ liệu không bị hiểu nhầm là câu lệnh
*/