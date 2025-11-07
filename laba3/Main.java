import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        OrderManager manager = new OrderManager();
        List<String> items1 = new ArrayList<>();
        items1.add("Книга");
        items1.add("Блокнот");
        Order o1 = new Order(1001, items1, "Улица Пушкина, дом 10", 29.99);

        List<String> items2 = new ArrayList<>();
        items2.add("Блокнот");
        Order o2 = new Order(1002, items2, "Проспект Ленина, дом 5", 20.50);

        manager.addOrder(o1);
        manager.addOrder(o2);

        System.out.println("OrderManager найден: " + manager.findOrder(1001));
        System.out.println("OrderManager найден: " + manager.findOrder(1002));
        System.out.println("OrderManager удаление 1002: " + manager.removeOrder(1002));
        System.out.println("OrderManager найден 1002: " + manager.findOrder(1002));

        // пример с собственной HashTable
        HashTable<Integer, Order> table = new HashTable<>();
        table.put(o1.getOrderNumber(), o1);
        table.put(o2.getOrderNumber(), o2);

        System.out.println("HashTable найден: " + table.get(1001));
        System.out.println("HashTable найден: " + table.get(1002));
        System.out.println("HashTable удаление 1002: " + table.remove(1002));
        System.out.println("HashTable  найден 1002: " + table.get(1002));
        System.out.println("HashTable размер: " + table.size());
    }
}