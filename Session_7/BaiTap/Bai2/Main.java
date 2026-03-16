package Session_7.BaiTap.Bai2;

public class Main {
    public static void main(String[] args) {
        double totalAmount = 1000000;

        OrderCalculator order1 = new OrderCalculator(new PercentageDiscount());
        System.out.println("Số tiền sau giảm: " + order1.calculateTotal(totalAmount));

        OrderCalculator order2 = new OrderCalculator(new FixedDiscount());
        System.out.println("Số tiền sau giảm: " + order2.calculateTotal(totalAmount));

        OrderCalculator order3 = new OrderCalculator(new NoDiscount());
        System.out.println("Số tiền sau giảm: " + order3.calculateTotal(totalAmount));

        OrderCalculator order4 = new OrderCalculator(new HolidayDiscount());
        System.out.println("Số tiền sau giảm: " + order4.calculateTotal(totalAmount));

    }
}