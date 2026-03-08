package Session_01;

import java.util.Scanner;

public class Bai1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Nhập năm sinh của bạn : ");
            String year = sc.nextLine();

            int input = Integer.parseInt(year);

            int age = 2026 - input;
            System.out.print("Tuổi của bạn là : " +age);

        }catch (NumberFormatException e){
            System.err.println("Lỗi , vui lòng nhập số");
        }finally {
            sc.close();
            System.out.println("\nThực hiện dọn dẹp tài nguyên trong finally...");
        }
    }
}
