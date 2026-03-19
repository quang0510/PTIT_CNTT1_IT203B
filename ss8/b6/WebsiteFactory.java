package b6;

public class WebsiteFactory implements SalesChannelFactory {
    public Discount createDiscount() { return () -> System.out.println("Áp dụng giảm giá 10%: 1.500.000"); }
    public Payment createPayment() { return (amt) -> System.out.println("Xử lý thanh toán thẻ tín dụng: " + amt); }
    public Notify createNotify() { return () -> System.out.println("Gửi email: Đơn hàng thành công"); }
}
