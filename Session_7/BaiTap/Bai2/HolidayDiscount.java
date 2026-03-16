package Session_7.BaiTap.Bai2;

public class HolidayDiscount implements DiscountStrategy{
    @Override
    public double applyDiscount(double totalAmount) {
        double result = totalAmount - (totalAmount * 0.15);
        return result;
    }
}