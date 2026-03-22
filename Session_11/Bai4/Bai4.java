package Session_11.Bai4;

import java.sql.*;
import java.util.Scanner;

public class Bai4 {
    // Hằng số cấu hình
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    // Tạo kết nối
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tên bệnh nhân: ");
        String input = scanner.nextLine();

        // Loại bỏ ký tự nguy hiểm
        input = input.replace("--", "")
                .replace(";", "")
                .replace("'", "");

        try {
            con = getConnection();
            stmt = con.createStatement();

            String sql = "SELECT * FROM patients WHERE patients_name LIKE " + "'%" + input + "%'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Tên: " + rs.getString("patients_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*
 Khi sử dụng nối chuỗi để tạo câu lệnh SQL, dữ liệu người dùng nhập vào sẽ được gắn trực tiếp vào câu lệnh.

Ví dụ: Nếu người dùng nhập: ' OR '1'='1

Câu lệnh SQL sẽ trở thành: SELECT * FROM patients WHERE name = '' OR '1'='1';

Phân tích:

'1'='1' luôn đúng (true)
Do có toán tử OR, toàn bộ điều kiện WHERE luôn đúng
Kết quả:

Hệ thống sẽ trả về toàn bộ dữ liệu trong bảng patients thay vì chỉ một bệnh nhân
Đây chính là lỗ hổng SQL Injection gây rò rỉ dữ liệu
Ngoài ra, ký tự -- có thể dùng để comment phần còn lại của câu lệnh, giúp hacker kiểm soát truy vấn hoàn toàn.
*/