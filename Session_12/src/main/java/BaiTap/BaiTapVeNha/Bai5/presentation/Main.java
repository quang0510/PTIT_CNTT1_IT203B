package BaiTap.BaiTapVeNha.Bai5.presentation;

import BaiTap.BaiTapVeNha.Bai5.dao.PatientDAO;
import BaiTap.BaiTapVeNha.Bai5.model.Patient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bai5";
        String user = "root";
        String password = "Sp@tiz0510";
        Scanner sc = new Scanner(System.in);

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PatientDAO dao = new PatientDAO(connection);

            int choice;
            do {
                System.out.println("\n===== MENU =====");
                System.out.println("1. Danh sách");
                System.out.println("2. Thêm");
                System.out.println("3. Cập nhật");
                System.out.println("4. Tính phí");
                System.out.println("5. Thoát");
                System.out.print("Chọn: ");
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        List<Patient> list = dao.getAll();
                        for (Patient p : list) {
                            System.out.printf("ID:%d | %s | %d | %s\n",
                                    p.getId(), p.getName(), p.getAge(), p.getDepartment());
                        }
                        break;

                    case 2:
                        System.out.print("Tên: ");
                        String name = sc.nextLine();

                        System.out.print("Tuổi: ");
                        int age = Integer.parseInt(sc.nextLine());

                        System.out.print("Khoa: ");
                        String dept = sc.nextLine();

                        dao.insert(new Patient(0, name, age, dept));
                        System.out.printf("Thêm thành công!\n");
                        break;

                    case 3:
                        System.out.print("ID: ");
                        int id = Integer.parseInt(sc.nextLine());

                        System.out.print("Khoa mới: ");
                        String newDept = sc.nextLine();

                        if (dao.updateDepartment(id, newDept)) {
                            System.out.printf("Cập nhật thành công!\n");
                        } else {
                            System.out.printf("Không tìm thấy!\n");
                        }
                        break;

                    case 4:
                        System.out.print("ID: ");
                        int pid = Integer.parseInt(sc.nextLine());

                        String fee = dao.calculateFee(pid);
                        System.out.printf("Viện phí: %s\n", fee);
                        break;

                    case 5:
                        System.out.printf("Thoát!\n");
                        break;
                }

            } while (choice != 5);

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
