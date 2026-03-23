package BaiTap.ThucHanh1.presentation;

import BaiTap.ThucHanh1.database.MyDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DemoStatement {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== HỆ THỐNG ĐĂNG NHẬP (BẢO MẬT KÉM) ===");
        System.out.print("Username: ");
        // nhập: ' OR '1'='1
        String user = scanner.nextLine();

        System.out.print("Password: ");

        String pass = scanner.nextLine();


        String sql = "SELECT * FROM Users WHERE username = '" + user + "' AND password = '" + pass + "'";

        System.out.println("\n Câu lệnh gửi tới MySQL: \n" + sql);

        try (Connection conn = MyDatabase.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- KẾT QUẢ TRUY XUẤT HỆ THỐNG ---");
            boolean found = false;

            // Dùng vòng lặp while để in ra TẤT CẢ các bản ghi bị "lộ"
            while (rs.next()) {
                found = true;
                System.out.println(" USERNAME: " + rs.getString("username") +
                        " | PASSWORD: " + rs.getString("password") +
                        " | HỌ TÊN: " + rs.getString("full_name") +
                        " | QUYỀN: " + rs.getString("role"));
            }

            if (found) {
                System.out.println("\n CẢNH BÁO: Đã lấy được dữ liệu mà không cần mật khẩu!");
            } else {
                System.out.println(" Thông tin không hợp lệ.");
            }

        } catch (Exception e) {
            System.err.println(" Lỗi: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
