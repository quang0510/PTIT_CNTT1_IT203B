package Session_11.Bai5;

import java.util.Scanner;

public class RikkeiCareApp {
    public static void main(String[] args) {
        DoctorDAO dao = new DoctorDAO();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Xem danh sách bác sĩ");
            System.out.println("2. Thêm bác sĩ mới");
            System.out.println("3. Thống kê chuyên khoa");
            System.out.println("4. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(sc.nextLine());
            try {
                switch (choice) {
                    case 1:
                        dao.getAllDoctors().forEach(System.out::println);
                        break;
                    case 2:
                        System.out.print("Nhập mã BS: "); String id = sc.nextLine();
                        System.out.print("Nhập tên BS: "); String name = sc.nextLine();
                        System.out.print("Chuyên khoa: "); String spec = sc.nextLine();
                        if (dao.addDoctor(new Doctor(id, name, spec)))
                            System.out.println("Thêm thành công!");
                        break;
                    case 3:
                        dao.showStatistics();
                        break;
                    case 4:
                        System.out.println("Cảm ơn bạn đã dùng hệ thống!");
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (Exception e) {
                System.err.println("[LỖI] Thao tác thất bại: " + e.getMessage());
            }
        }
    }
}
