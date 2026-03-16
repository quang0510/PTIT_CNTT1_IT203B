package Session_7.BaiTap;

import java.util.*;

interface DiscountB6 { double apply(double amt); }
interface PaymentB6 { void pay(double amt); }
interface NotifyB6 { void send(String msg); }

class WebDiscountB6 implements DiscountB6 { public double apply(double amt) { return amt * 0.9; } }
class WebPaymentB6 implements PaymentB6 { public void pay(double amt) { System.out.println("Thanh toán Online qua cổng Web: " + (long)amt); } }
class WebNotifyB6 implements NotifyB6 { public void send(String msg) { System.out.println("Gửi Email xác nhận: " + msg); } }

class MobileDiscountB6 implements DiscountB6 { public double apply(double amt) { return amt * 0.85; } }
class MobilePaymentB6 implements PaymentB6 { public void pay(double amt) { System.out.println("Thanh toán MoMo tích hợp: " + (long)amt); } }
class MobileNotifyB6 implements NotifyB6 { public void send(String msg) { System.out.println("Gửi Push Notification: " + msg); } }

class POSDiscountB6 implements DiscountB6 { public double apply(double amt) { return amt - 20000; } }
class POSPaymentB6 implements PaymentB6 { public void pay(double amt) { System.out.println("Thanh toán tiền mặt tại quầy: " + (long)amt); } }
class POSNotifyB6 implements NotifyB6 { public void send(String msg) { System.out.println("In hóa đơn giấy tại chỗ: " + msg); } }

interface SalesChannelFactoryB6 {
    DiscountB6 createDiscount();
    PaymentB6 createPayment();
    NotifyB6 createNotify();
}

class WebsiteFactoryB6 implements SalesChannelFactoryB6 {
    public DiscountB6 createDiscount() { return new WebDiscountB6(); }
    public PaymentB6 createPayment() { return new WebPaymentB6(); }
    public NotifyB6 createNotify() { return new WebNotifyB6(); }
}

class MobileAppFactoryB6 implements SalesChannelFactoryB6 {
    public DiscountB6 createDiscount() { return new MobileDiscountB6(); }
    public PaymentB6 createPayment() { return new WebPaymentB6(); } // Dùng cổng chung
    public NotifyB6 createNotify() { return new MobileNotifyB6(); }
}

class StorePOSFactoryB6 implements SalesChannelFactoryB6 {
    public DiscountB6 createDiscount() { return new POSDiscountB6(); }
    public PaymentB6 createPayment() { return new POSPaymentB6(); }
    public NotifyB6 createNotify() { return new POSNotifyB6(); }
}

public class Bai6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SalesChannelFactoryB6 factory = null;

        while (true) {
            System.out.println("\n--- CHỌN KÊNH BÁN HÀNG ---");
            System.out.println("1. Website | 2. Mobile App | 3. Store POS | 0. Thoát");
            int choice = Integer.parseInt(sc.nextLine());

            if (choice == 1) {
                factory = new WebsiteFactoryB6();
                System.out.println("Bạn đã chọn kênh Website");
            } else if (choice == 2) {
                factory = new MobileAppFactoryB6();
                System.out.println("Bạn đã chọn kênh Mobile App");
            } else if (choice == 3) {
                factory = new StorePOSFactoryB6();
                System.out.println("Bạn đã chọn kênh Store POS");
            } else break;

            double total = 1000000;
            DiscountB6 d = factory.createDiscount();
            PaymentB6 p = factory.createPayment();
            NotifyB6 n = factory.createNotify();

            double finalAmt = d.apply(total);
            System.out.println("Tổng tiền gốc: " + (long)total);
            System.out.println("Sau giảm giá đặc thù theo kênh: " + (long)finalAmt);
            p.pay(finalAmt);
            n.send("Đơn hàng thành công!");
        }
    }
}