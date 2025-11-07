import java.util.HashMap;

public class OrderManager {
    private HashMap<Integer, Order> map;

    public OrderManager() {
        map = new HashMap<>();
    }

    public void addOrder(Order order) {
        map.put(order.getOrderNumber(), order);
    }

    public Order findOrder(int orderNumber) {
        return map.get(orderNumber);
    }

    public boolean removeOrder(int orderNumber) {
        return map.remove(orderNumber) != null;
    }
}