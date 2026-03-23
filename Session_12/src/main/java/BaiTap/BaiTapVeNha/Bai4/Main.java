package BaiTap.BaiTapVeNha.Bai4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bai4";
        String user = "root";
        String password = "Sp@tiz0510";

        List<TestResult> list = new ArrayList<>();

        // giả lập dữ liệu
        list.add(new TestResult("A"));
        list.add(new TestResult("B"));
        list.add(new TestResult("C"));

        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            // Khởi tạo 1 lần duy nhất
            String sql = "insert into results(data) values(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Lặp chỉ để set dữ liệu
            for (TestResult e : list) {
                preparedStatement.setString(1, e.getData());
                preparedStatement.executeUpdate();
            }

            preparedStatement.close();
            connection.close();

            System.out.printf("Insert thành công!\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

// class phụ
class TestResult {
    private String data;

    public TestResult(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}

/*
 * PHÂN TÍCH:
 * 1. Sự lãng phí tài nguyên: Khi dùng Statement trong vòng lặp 1.000 lần, Database Server buộc phải
 * thực hiện lại quy trình: Kiểm tra cú pháp (Parse), Kiểm tra quyền hạn, và Lập kế hoạch thực thi (Execution Plan)
 * cho 1.000 câu lệnh khác nhau (do chuỗi SQL thay đổi mỗi lần nối thêm dữ liệu).
 *
 * 2. Tác động: Điều này tiêu tốn cực lớn tài nguyên CPU và RAM của Database, khiến tốc độ xử lý chậm đi hàng chục lần.
 * * PHẦN 2 - THỰC THI (Tối ưu hóa):
 * Sử dụng PreparedStatement để biên dịch cấu trúc SQL 1 lần duy nhất ngoài vòng lặp.
 * Trong vòng lặp chỉ truyền tham số và thực thi, giúp giảm tải tối đa cho Database Server.
 */