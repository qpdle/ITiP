class SumWorker extends Thread {
    private int[] array;
    private int startIndex;
    private int endIndex;
    private int partialSum;
    public SumWorker(int[] array, int startIndex, int endIndex) {
        this.array = array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }
    public void run() {
        partialSum = 0;
        for (int i = startIndex; i < endIndex; i++) {
            partialSum += array[i];
        }
    }
    public int getPartialSum() {
        return partialSum;
    }
}
public class SumArrayTwoThreads {
    public static void main(String[] args) throws InterruptedException {
        int[] numbers = {2, 4, 6, 8, 10, 12};
        SumWorker firstThread = new SumWorker(numbers, 0, numbers.length / 2);
        SumWorker secondThread = new SumWorker(numbers, numbers.length / 2, numbers.length);
        firstThread.start();
        secondThread.start();
        firstThread.join();
        secondThread.join();
        int totalSum = firstThread.getPartialSum() + secondThread.getPartialSum();
        System.out.println("Сумма элементов массива: " + totalSum);
    }
}