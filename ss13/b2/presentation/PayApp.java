package ss13.b2.presentation;

import ss13.b2.business.PaymentService;

/*
 * GIAI THICH LOGIC (PHAN 1):
 * 1. Tai sao chi in ra loi la sai: Neu chi dung System.out.println trong catch thi Database 
 * van dang giu cac thay doi tam thoi (tru tien) va khoa tai nguyen lai. 
 * Dieu nay gay lang phi tai nguyen va treo ket noi.
 * 2. Hanh dong thiet yeu bi bo quen: Do la lenh rollback. 
 * Phai goi rollback de huy bo moi thay doi tam thoi va dua DB ve trang thai an toan.
 */

public class PayApp {
    public static void main(String[] args) {
        PaymentService s = new PaymentService();
        System.out.println("thu nghiem thanh toan loi");
        s.pay(1, 101, 2000000.00);
    }
}
