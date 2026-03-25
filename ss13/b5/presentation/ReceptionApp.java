package ss13.b5.presentation;
import java.util.Scanner;
import ss13.b5.business.ReceptionService;

/*
 * PHAN TICH:
 * 1. Rui ro: Nhap chu vao o so, giuong bi nguoi khac dat truoc, thieu ket noi giua chung.
 * 2. Luong: Service mo conn -> tat autocommit -> goi DAO thuc hien 3 buoc -> commit/rollback.
 * 3. DB: Bang Giuong (id, status), BenhNhan (id, ten, tuoi, gId), TaiChinh (id, bnId, tien).
 */

public class ReceptionApp {
    public static void main(String[] args) {
        ReceptionService s = new ReceptionService();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. xem giuong | 2. tiep nhan | 3. thoat");
            try {
                int ch = Integer.parseInt(sc.nextLine());
                if (ch == 1) s.xemGiuong();
                else if (ch == 2) {
                    System.out.print("ten: "); String t = sc.nextLine();
                    System.out.print("tuoi: "); int tuoi = Integer.parseInt(sc.nextLine());
                    System.out.print("ma g: "); int g = Integer.parseInt(sc.nextLine());
                    System.out.print("tien: "); double m = Double.parseDouble(sc.nextLine());
                    s.vaoVien(t, tuoi, g, m);
                } else if (ch == 3) break;
            } catch (Exception e) {
                System.out.println("nhap sai kieu du lieu");
            }
        }
    }
}
