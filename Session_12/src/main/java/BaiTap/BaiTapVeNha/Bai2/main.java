package BaiTap.BaiTapVeNha.Bai2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bai2";
        String user =  "root";
        String password = "Sp@tiz0510";

        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập patientId: ");
        int patientId = scanner.nextInt();

        System.out.println("Nhập vào nhiệt độ : ");
        double temp = scanner.nextDouble();

        System.out.println("Nhập vào nhịp tim : ");
        int heartRate = scanner.nextInt();

        try{
            Connection connection = DriverManager.getConnection(url , user , password);
            String sql = " update Vitals set temp = ? and heartRate = ? where patientId = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDouble(1, temp);
            preparedStatement.setInt(2, heartRate);
            preparedStatement.setInt(3, patientId);

            int rs=  preparedStatement.executeUpdate();

            if (rs > 0) {
                System.out.println("Cập nhật thành công");
            } else {
                System.out.println("Cập nhật thất bại!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
/*
PreparedStatement truyền dữ liệu theo kiểu (type-safe) thay vì chuỗi
setDouble(), setInt() giúp:
Tránh lỗi định dạng số (. vs ,)

Không phụ thuộc Locale hệ điều hành
Đảm bảo dữ liệu luôn đúng chuẩn SQL
*/