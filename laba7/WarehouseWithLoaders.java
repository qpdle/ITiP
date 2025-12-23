import java.util.*;
import java.util.concurrent.locks.*;
class Warehouse {
    private List<Integer> goods;
    private int currentWeight;
    private final int MAX_WEIGHT = 150;
    private ReentrantLock lock;
    private Condition canLoad;
    public Warehouse(List<Integer> goods) {
        this.goods = new ArrayList<>(goods);
        this.currentWeight = 0;
        this.lock = new ReentrantLock();
        this.canLoad = lock.newCondition();
    }
    public void loadGoods(String loaderName) throws InterruptedException {
        while (true) {
            lock.lock();
            try {
                if (goods.isEmpty()) {
                    return;
                }
                int weight = goods.get(0);
                while (currentWeight + weight > MAX_WEIGHT) {
                    canLoad.await();
                }
                goods.remove(0);
                currentWeight += weight;
                System.out.println(loaderName + " взял товар весом " + weight + ", Текущий вес: " + currentWeight);
                if (currentWeight == MAX_WEIGHT) {
                    System.out.println("Отправка на другой склад");
                    currentWeight = 0;
                    canLoad.signalAll();
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
class Loader extends Thread {
    private Warehouse warehouse;
    public Loader(String name, Warehouse warehouse) {
        super(name);
        this.warehouse = warehouse;
    }
    public void run() {
        try {
            warehouse.loadGoods(getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class WarehouseWithLoaders {
    public static void main(String[] args) {
        List<Integer> goods = Arrays.asList(40, 30, 50, 20, 60, 40);
        Warehouse warehouse = new Warehouse(goods);
        new Loader("Грузчик 1", warehouse).start();
        new Loader("Грузчик 2", warehouse).start();
        new Loader("Грузчик 3", warehouse).start();
    }
}