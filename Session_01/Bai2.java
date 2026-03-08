package Session_01;

import java.util.Scanner;

public class Bai2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập tổng số người : ");
        int sumPerson = sc.nextInt();

        System.out.print("Nhập số lượng nhóm muốn chia : ");
        int sumGroup = sc.nextInt();

        try {
            int prsForGrp = sumPerson  / sumGroup ;
            System.out.printf("số người mỗi nhóm là : %d", prsForGrp);

        }catch (ArithmeticException e){
            System.err.println("không thể chia hết cho 0");
        }finally {
            sc.close();
            System.out.println("\nThực hiện dọn dẹp tài nguyên trong finally...");
        }
    }
}
