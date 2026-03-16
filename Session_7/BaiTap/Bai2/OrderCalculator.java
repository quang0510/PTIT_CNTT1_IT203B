package Session_7.BaiTap.Bai2;

public class OrderCalculator {
    private DiscountStrategy discountStrategy;

    public OrderCalculator(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }


    public double calculateTotal(double totalAmount) {
        return discountStrategy.applyDiscount(totalAmount);
    }
}