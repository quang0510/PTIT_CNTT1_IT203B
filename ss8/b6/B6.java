package b6;

public class B6 {
    public static void main(String[] args) {
        SalesChannelFactory factory = new WebsiteFactory();
        System.out.println("Bạn đã chọn kênh Website");
        factory.createDiscount().apply();
        factory.createPayment().pay(13500000);
        factory.createNotify().announce();
    }
}
