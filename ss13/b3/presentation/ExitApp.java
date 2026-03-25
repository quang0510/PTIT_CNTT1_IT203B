package ss13.b3.presentation;

import ss13.b3.business.ExitService;

/*
 * BAO CAO PHAN TICH:
 * 1. I/O: Input la ma benh nhan int va tien vien phi double. Output la log thanh cong hoac thong bao loi logic rollback.
 * 2. Giai phap: Dung DbConn quan ly AutoCommit false. Dung Try Catch de nem Exception chu dong khi check so du va RowAffected = 0.
 * 3. Thiet ke: Mo ket noi -> Tat AutoCommit -> Check tien (Bay 1) -> Update tien -> Update giuong (Bay 2) -> Update status -> Commit.
 */

public class ExitApp {
    public static void main(String[] args) {
        ExitService s = new ExitService();

        System.out.println("case 1 thieu tien");
        s.process(1, 9999999.0);

        System.out.println("case 2 thanh cong");
        s.process(1, 1000.0);
    }
}