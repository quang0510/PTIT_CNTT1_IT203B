package Session_7.BaiTap.Bai2;


public class FixedDiscount implements DiscountStrategy{
    @Override
    public double applyDiscount(double totalAmount) {
        double result = totalAmount - 50000;
        return result;
    }
}