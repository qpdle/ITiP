import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Order {
    private int orderNumber;
    private List<String> items;
    private String address;
    private double totalCost;

    public Order(int orderNumber, List<String> items, String address, double totalCost) {
        this.orderNumber = orderNumber;
        this.items = new ArrayList<>(items);
        this.address = address;
        this.totalCost = totalCost;
    }

    public int getOrderNumber() { return orderNumber; }
    public List<String> getItems() { return new ArrayList<>(items); }
    public String getAddress() { return address; }
    public double getTotalCost() { return totalCost; }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", items=" + items +
                ", address='" + address + '\'' +
                ", totalCost=" + totalCost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderNumber == order.orderNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber);
    }
}