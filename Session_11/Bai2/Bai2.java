package Session_11.Bai2;

import java.sql.*;

public class Bai2 {
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

        try{
            con = getConnection();
            stmt = con.createStatement();

            String sql = "SELECT drug_name, quantity FROM drugs";
            rs = stmt.executeQuery(sql);

            while (rs.next()){
                String name = rs.getString("drug_name");
                int quantity = rs.getInt("quantity");
                System.out.println("Tên thuốc: " + name + " | Số lượng: " + quantity);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
/*
Lệnh if không đủ để xử lý yêu cầu "in danh sách" vì:

if(rs.next()) chỉ kiểm tra và di chuyển con trỏ xuống đúng 1 dòng đầu tiên.
Sau khi thực hiện xong, chương trình không tiếp tục đọc các dòng tiếp theo nên chỉ in được 1 bản ghi.
Cơ chế con trỏ của ResultSet:

Ban đầu con trỏ nằm trước dòng đầu tiên.
Mỗi lần gọi next() → con trỏ di chuyển xuống 1 dòng.
Nếu còn dữ liệu → trả về true.
Nếu hết dữ liệu → trả về false.
Vì vậy, để duyệt toàn bộ dữ liệu, cần dùng vòng lặp (while) thay vì if.
*/