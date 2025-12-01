public class ArrayAverage {
    public static void main(String[] args) {
        String[] arr = {"1", "2", "three", "4", "5"};
        double sum = 0;
        int count = 0;
        try {
            for (int i = 0; i < arr.length; i++) {
                int value = Integer.parseInt(arr[i]);
                sum += value;
                count++;
            }
            if (count == 0) {
                throw new ArithmeticException("Нет числовых элементов для вычисления среднего");
            }
            double average = sum / count;
            System.out.println("Среднее арифметическое: " + average);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("Ошибка доступа к массиву: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            System.err.println("Неверный формат числа в массиве: " + ex.getMessage());
        } catch (ArithmeticException ex) {
            System.err.println("Невозможно вычислить среднее: " + ex.getMessage());
        }
    }
}