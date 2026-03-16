package Session_7.BaiTap.Bai3;

public interface CardPayable extends PaymentMethod {
    void processCreditCard(double amount);
}