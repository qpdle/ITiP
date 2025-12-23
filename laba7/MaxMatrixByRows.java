class RowMaxFinder extends Thread {
    private int[] row;
    private int maxValue;
    public RowMaxFinder(int[] row) {
        this.row = row;
    }
    public void run() {
        maxValue = row[0];
        for (int value : row) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
    }
    public int getMaxValue() {
        return maxValue;
    }
}
public class MaxMatrixByRows {
    public static void main(String[] args) throws InterruptedException {
        int[][] matrix = {
                {3, 7, 1},
                {9, 2, 5},
                {4, 6, 8}
        };
        RowMaxFinder[] threads = new RowMaxFinder[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            threads[i] = new RowMaxFinder(matrix[i]);
            threads[i].start();
        }
        int globalMax = Integer.MIN_VALUE;
        for (RowMaxFinder thread : threads) {
            thread.join();
            if (thread.getMaxValue() > globalMax) {
                globalMax = thread.getMaxValue();
            }
        }
        System.out.println("Максимальный элемент матрицы: " + globalMax);
    }
}