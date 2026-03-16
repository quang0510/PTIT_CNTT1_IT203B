package Session_7.BaiTap.Bai2;

public class NoDiscount implements DiscountStrategy{
    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount;
    }
}