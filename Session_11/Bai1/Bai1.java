package Session_11.Bai1;

import java.sql.*;

public class Bai1 {
    // Hằng số cấu hình
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    // Tạo kết nối
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void getPatients() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            stmt = conn.createStatement();

            String sql = "SELECT * FROM patients";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getString("patients_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng theo thứ tự ngược
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        getPatients();
    }
}

/*
Việc khởi tạo kết nối liên tục mà không đóng (close) hoặc không quản lý tập trung sẽ gây nguy hiểm cho hệ thống vì:

Mỗi kết nối đến Database đều tiêu tốn tài nguyên (bộ nhớ, socket, thread).
Nếu không đóng kết nối, các kết nối sẽ bị “rò rỉ” (connection leak) và tích tụ theo thời gian.
Sau một thời gian, Database sẽ đạt giới hạn số lượng kết nối cho phép → không thể tạo thêm kết nối mới.
Điều này dẫn đến lỗi như "Communications link failure" và làm hệ thống bị treo.
Trong hệ thống y tế cần hoạt động 24/7, việc này đặc biệt nguy hiểm vì có thể làm gián đoạn truy xuất hồ sơ bệnh nhân, ảnh hưởng trực tiếp đến việc khám chữa bệnh.
* */