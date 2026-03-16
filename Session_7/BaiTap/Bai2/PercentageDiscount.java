package Session_7.BaiTap.Bai2;

public class PercentageDiscount implements DiscountStrategy{
    @Override
    public double applyDiscount(double totalAmount) {
        double result = totalAmount - (totalAmount * 0.01);
        return result;
    }
}