package Session_7.BaiTap.Bai3;

public class CreditCardPayment implements CardPayable{

    @Override
    public void processCreditCard(double amount) {
        System.out.println("Xử lý thanh toán thẻ tín dụng: " + amount + " - Thành công");
    }

    @Override
    public void pay(double amount) {
        processCreditCard(amount);
    }
}