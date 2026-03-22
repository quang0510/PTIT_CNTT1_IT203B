package Session_11.Bai3;

import java.sql.*;

public class Bai3 {
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

        String bedId = "Bed_999";

        try{
            con = getConnection();
            stmt = con.createStatement();

            String sql = "UPDATE beds SET bed_status = 'Đang sử dụng' WHERE bed_id = '" + bedId + "'";

            int rowsAffected = stmt.executeUpdate(sql);
            if(rowsAffected > 0) {
                System.out.println("Cập nhật thành công trạng thái giường");
            } else {
                System.out.println("Lỗi: Mã giường không tồn tại");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

/*
Giá trị trả về của phương thức executeUpdate() là một số nguyên (int), đại diện cho số lượng dòng dữ liệu bị tác động bởi câu lệnh SQL (INSERT, UPDATE, DELETE).

Nếu giá trị > 0: Có ít nhất một dòng đã được cập nhật → thao tác thành công.
Nếu giá trị = 0: Không có dòng nào bị tác động → có thể do điều kiện WHERE không khớp (ví dụ: mã giường không tồn tại).
Trong bài toán này, nếu executeUpdate() trả về 0, ta có thể kết luận rằng mã giường không tồn tại và cần thông báo cho y tá để tránh hiểu nhầm là cập nhật thành công.
*/