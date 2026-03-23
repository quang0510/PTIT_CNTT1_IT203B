package BaiTap.ThucHanh1.presentation;

import BaiTap.ThucHanh1.database.MyDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class DemoPrepareStatement {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("=== HỆ THỐNG ĐĂNG NHẬP (BẢO MẬT KÉM) ===");
//        System.out.print("Username: ");
//        // nhập: ' OR '1'='1
//        String user = scanner.nextLine();
//
//        System.out.print("Password: ");
//
//        String pass = scanner.nextLine();
//
//
//        String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
//
//        System.out.println("\n Câu lệnh gửi tới MySQL: \n" + sql);
//
//        try (Connection conn = MyDatabase.getConnection())
//              {
//
//                  PreparedStatement  preparedStatement = conn.prepareStatement(sql);
//                  preparedStatement.setString(1, user);
//                  preparedStatement.setString(2, pass);
//                  ResultSet rs = preparedStatement.executeQuery();
//            System.out.println("\n--- KẾT QUẢ TRUY XUẤT HỆ THỐNG ---");
//            boolean found = false;
//
//            // Dùng vòng lặp while để in ra TẤT CẢ các bản ghi bị "lộ"
//            while (rs.next()) {
//                found = true;
//                System.out.println(" USERNAME: " + rs.getString("username") +
//                        " | PASSWORD: " + rs.getString("password") +
//                        " | HỌ TÊN: " + rs.getString("full_name") +
//                        " | QUYỀN: " + rs.getString("role"));
//            }
//
//            if (found) {
//                System.out.println("\n CẢNH BÁO: Đã lấy được dữ liệu mà không cần mật khẩu!");
//            } else {
//                System.out.println(" Thông tin không hợp lệ.");
//            }
//
//        } catch (Exception e) {
//            System.err.println(" Lỗi: " + e.getMessage());
//        } finally {
//            scanner.close();
//        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id bệnh nhân : ");
        int id = Integer.parseInt(scanner.nextLine());
        String sql = "select * from patients where patient_id = ?";
        try (
                Connection connection = MyDatabase.getConnection();

        ) {
            PreparedStatement preparedStatement =  connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int idPatientd = resultSet.getInt("patient_id");
                String name = resultSet.getString("full_name");
                System.out.println("Thông tin bệnh nhân tìm thấy là :");
                System.out.printf("|ID : %-5d | Name : %-30s" ,idPatientd,name );
            }else {
                System.out.println("Không tìm thấy bệnh nhân có id : " + id);
            }
            resultSet.close();
        }catch (Exception e){
            System.out.println("Có lỗi : " + e.getMessage());
        }
        scanner.close();
    }
}
