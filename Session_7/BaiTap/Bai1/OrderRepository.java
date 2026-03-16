package Session_7.BaiTap.Bai1;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private List<Order> orders;

    public OrderRepository() {
        orders = new ArrayList<>();
    }

    public void saveOrder(Order order) {
        orders.add(order);
    }

    public Order findOrderById(String orderId) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

}
