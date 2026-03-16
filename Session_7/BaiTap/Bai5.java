package Session_7.BaiTap;

import java.util.*;

class ProductB5 {
    String id, name, category;
    double price;
    public ProductB5(String id, String name, double price, String category) {
        this.id = id; this.name = name; this.price = price; this.category = category;
    }
}

class CustomerB5 {
    String name, email, phone;
    public CustomerB5(String name, String email, String phone) {
        this.name = name; this.email = email; this.phone = phone;
    }
}

class OrderItemB5 {
    ProductB5 product;
    int quantity;
    public OrderItemB5(ProductB5 product, int quantity) {
        this.product = product; this.quantity = quantity;
    }
    public double getSubTotal() { return product.price * quantity; }
}

class OrderB5 {
    String orderId;
    CustomerB5 customer;
    List<OrderItemB5> items = new ArrayList<>();
    double totalAmount, discountAmount, finalAmount;
    public OrderB5(String orderId, CustomerB5 customer) {
        this.orderId = orderId; this.customer = customer;
    }
    public void addItem(OrderItemB5 item) {
        this.items.add(item);
    }
}

interface DiscountStrategyB5 {
    double calculateDiscount(double amount);
    String getName();
}

class PercentageDiscountB5 implements DiscountStrategyB5 {
    double rate;
    public PercentageDiscountB5(double rate) { this.rate = rate; }
    public double calculateDiscount(double amount) { return amount * rate / 100; }
    public String getName() { return rate + "%"; }
}

class FixedDiscountB5 implements DiscountStrategyB5 {
    double amount;
    public FixedDiscountB5(double amount) { this.amount = amount; }
    public double calculateDiscount(double amount) { return Math.min(this.amount, amount); }
    public String getName() { return "Cố định " + amount; }
}

class HolidayDiscountB5 implements DiscountStrategyB5 {
    public double calculateDiscount(double amount) { return amount * 0.15; }
    public String getName() { return "Ngày lễ 15%"; }
}

interface PaymentMethodB5 {
    void process(double amount);
    String getName();
}

class CODPaymentB5 implements PaymentMethodB5 {
    public void process(double amt) { System.out.println("Thanh toán COD: " + (long)amt); }
    public String getName() { return "COD"; }
}

class CreditCardPaymentB5 implements PaymentMethodB5 {
    public void process(double amt) { System.out.println("Thanh toán Thẻ tín dụng: " + (long)amt); }
    public String getName() { return "Thẻ tín dụng"; }
}

class MomoPaymentB5 implements PaymentMethodB5 {
    public void process(double amt) { System.out.println("Thanh toán Momo: " + (long)amt); }
    public String getName() { return "Momo"; }
}

interface OrderRepositoryB5 {
    void save(OrderB5 order);
    List<OrderB5> getAll();
}

class FileOrderRepositoryB5 implements OrderRepositoryB5 {
    List<OrderB5> list = new ArrayList<>();
    public void save(OrderB5 o) { list.add(o); System.out.println("Đã lưu đơn hàng " + o.orderId + " vào file"); }
    public List<OrderB5> getAll() { return list; }
}

interface NotificationServiceB5 {
    void send(String msg, CustomerB5 c);
}

class EmailNotificationB5 implements NotificationServiceB5 {
    public void send(String m, CustomerB5 c) { System.out.println("Đã gửi email xác nhận đến " + c.email); }
}

class InvoiceGeneratorB5 {
    public void generate(OrderB5 o) {
        System.out.println("\n=== HÓA ĐƠN ===");
        System.out.println("Khách: " + o.customer.name);
        for (OrderItemB5 i : o.items) {
            System.out.println(i.product.id + " - SL: " + i.quantity + " - Đơn giá: " + (long)i.product.price);
        }
        System.out.println("Tổng tiền: " + (long)o.totalAmount);
        System.out.println("Giảm giá: " + (long)o.discountAmount);
        System.out.println("Cần thanh toán: " + (long)o.finalAmount);
    }
}

class OrderServiceB5 {
    OrderRepositoryB5 repo;
    NotificationServiceB5 notify;
    InvoiceGeneratorB5 inv;
    public OrderServiceB5(OrderRepositoryB5 r, NotificationServiceB5 n, InvoiceGeneratorB5 i) {
        this.repo = r; this.notify = n; this.inv = i;
    }
    public void createOrder(OrderB5 o, DiscountStrategyB5 d, PaymentMethodB5 p) {
        o.totalAmount = 0;
        for(OrderItemB5 item : o.items) o.totalAmount += item.getSubTotal();
        o.discountAmount = d.calculateDiscount(o.totalAmount);
        o.finalAmount = o.totalAmount - o.discountAmount;
        inv.generate(o);
        p.process(o.finalAmount);
        repo.save(o);
        notify.send("Thành công", o.customer);
    }
}

public class Bai5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<ProductB5> products = new ArrayList<>();
        List<CustomerB5> customers = new ArrayList<>();
        OrderServiceB5 service = new OrderServiceB5(new FileOrderRepositoryB5(), new EmailNotificationB5(), new InvoiceGeneratorB5());

        while (true) {
            System.out.println("\n1.Thêm SP | 2.Thêm Khách | 3.Tạo Đơn | 4.Xem Đơn | 5.Doanh thu | 0.Thoát");
            int c = Integer.parseInt(sc.nextLine());
            if (c == 0) break;

            if (c == 1) {
                System.out.print("Mã: "); String id = sc.nextLine();
                System.out.print("Tên: "); String n = sc.nextLine();
                System.out.print("Giá: "); double p = Double.parseDouble(sc.nextLine());
                products.add(new ProductB5(id, n, p, "Điện tử"));
                System.out.println("Đã thêm SP " + id);
            } else if (c == 2) {
                System.out.print("Tên: "); String n = sc.nextLine();
                System.out.print("Email: "); String e = sc.nextLine();
                customers.add(new CustomerB5(n, e, "0123456789"));
                System.out.println("Đã thêm khách hàng");
            } else if (c == 3) {
                if (products.isEmpty() || customers.isEmpty()) {
                    System.out.println("Lỗi: Phải có ít nhất 1 SP và 1 Khách!");
                    continue;
                }
                OrderB5 o = new OrderB5("ORD001", customers.get(0));
                o.addItem(new OrderItemB5(products.get(0), 1));
                service.createOrder(o, new PercentageDiscountB5(10), new CreditCardPaymentB5());
            } else if (c == 4) {
                for(OrderB5 ord : service.repo.getAll())
                    System.out.println(ord.orderId + " - " + ord.customer.name + " - " + (long)ord.finalAmount);
            } else if (c == 5) {
                double total = 0;
                for(OrderB5 ord : service.repo.getAll()) total += ord.finalAmount;
                System.out.println("Tổng doanh thu: " + (long)total);
            }
        }
    }
}