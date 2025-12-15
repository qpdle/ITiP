import java.util.LinkedHashMap;
import java.util.Map;
public class ShopSales {
    private LinkedHashMap<String, Integer> sales = new LinkedHashMap<>();
    private LinkedHashMap<String, Double> prices = new LinkedHashMap<>();
    public void addProduct(String name, double price) {
        prices.put(name, price);
    }
    public void sellProduct(String name) {
        sales.put(name, sales.getOrDefault(name, 0) + 1);
    }
    public void printSales() {
        System.out.println("Проданные товары:");
        for (Map.Entry<String, Integer> entry : sales.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
    public double getTotalSum() {
        double sum = 0;
        for (Map.Entry<String, Integer> entry : sales.entrySet()) {
            sum += prices.get(entry.getKey()) * entry.getValue();
        }
        return sum;
    }
    public String getMostPopularProduct() {
        String popular = null;
        int max = 0;
        for (Map.Entry<String, Integer> entry : sales.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                popular = entry.getKey();
            }
        }
        return popular;
    }
    public static void main(String[] args) {
        ShopSales shop = new ShopSales();
        shop.addProduct("Хлеб", 40);
        shop.addProduct("Молоко", 60);
        shop.addProduct("Яблоки", 100);
        shop.sellProduct("Хлеб");
        shop.sellProduct("Хлеб");
        shop.sellProduct("Молоко");
        shop.sellProduct("Яблоки");
        shop.sellProduct("Яблоки");
        shop.printSales();
        System.out.println("Общая сумма: " + shop.getTotalSum());
        System.out.println("Самый популярный товар: " + shop.getMostPopularProduct());
    }
}