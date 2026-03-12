package Session_04.KiemTra;

import java.sql.ClientInfoStatus;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {

        System.out.print("===== PRODUCT MANAGEMENT SYSTEM =====\n");
        System.out.println("1. Thêm sản phẩm mới");
        System.out.println("2. Hiển thị danh sách sản phẩm");
        System.out.println("3. Cập nhật số lượng theo ID");
        System.out.println("4. Xóa sản phẩm đã hết hàng");
        System.out.println("5. Thoát chương trình");
        System.out.print("Mời bạn nhập lựa chọn : ");
        choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice){
            case 1 :

                break;
            case 2 :
                break;
            case 3 :
                break;
            case 4 :
                break;
            case 5 :
                System.out.println("Thoát");
                break;
            default:
                System.out.println("lựa chọn k hợp lệ , vui lòng nhập lại ");
        }

        }while (choice != 5);


    }
}
