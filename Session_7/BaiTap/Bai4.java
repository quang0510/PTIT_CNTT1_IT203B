package Session_7.BaiTap;

import java.util.*;

class OrderB4 {
    private String code;
    public OrderB4(String code) { this.code = code; }
    public String getCode() { return code; }
}

interface OrderRepositoryB4 {
    void save(OrderB4 order);
}

interface NotificationServiceB4 {
    void send(String message, String recipient);
}

class FileOrderRepositoryB4 implements OrderRepositoryB4 {
    @Override
    public void save(OrderB4 order) {
        System.out.println("Lưu đơn hàng vào file: " + order.getCode());
    }
}

class DatabaseOrderRepositoryB4 implements OrderRepositoryB4 {
    @Override
    public void save(OrderB4 order) {
        System.out.println("Lưu đơn hàng vào database: " + order.getCode());
    }
}

class EmailServiceB4 implements NotificationServiceB4 {
    @Override
    public void send(String message, String recipient) {
        System.out.println("Gửi email: " + message);
    }
}

class SMSNotificationB4 implements NotificationServiceB4 {
    @Override
    public void send(String message, String recipient) {
        System.out.println("Gửi SMS: " + message);
    }
}

class OrderServiceB4 {
    private OrderRepositoryB4 repository;
    private NotificationServiceB4 notification;

    public OrderServiceB4(OrderRepositoryB4 repository, NotificationServiceB4 notification) {
        this.repository = repository;
        this.notification = notification;
    }

    public void createOrder(OrderB4 order) {
        repository.save(order);
        notification.send("Đơn hàng " + order.getCode() + " đã được tạo", "khachhang@example.com");
    }
}

public class Bai4 {
    public static void main(String[] args) {
        System.out.println("Cấu hình 1: File + Email");
        OrderServiceB4 service1 = new OrderServiceB4(new FileOrderRepositoryB4(), new EmailServiceB4());
        service1.createOrder(new OrderB4("ORD001"));

        System.out.println("\nCấu hình 2: Database + SMS");
        OrderServiceB4 service2 = new OrderServiceB4(new DatabaseOrderRepositoryB4(), new SMSNotificationB4());
        service2.createOrder(new OrderB4("ORD002"));
    }
}