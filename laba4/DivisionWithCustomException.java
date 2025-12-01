import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class CustomDivisionException extends Exception {
    public CustomDivisionException(String message) {
        super(message);
    }
}

public class DivisionWithCustomException {
    public static double divide(int a, int b) throws CustomDivisionException {
        if (b == 0) {
            throw new CustomDivisionException("Деление на ноль запрещено: делитель = 0");
        }
        return (double) a / b;
    }

    public static void logException(Exception ex) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = LocalDateTime.now().format(formatter);
        try (FileWriter fw = new FileWriter("exceptions.log", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println("[" + time + "] Exception: " + ex.getClass().getName());
            pw.println("Message: " + ex.getMessage());
            for (StackTraceElement st : ex.getStackTrace()) {
                pw.println("\tat " + st.toString());
            }
        } catch (IOException ioEx) {
            System.err.println("Не удалось записать лог: " + ioEx.getMessage());
        }
    }

    public static void main(String[] args) {
        int x = 10;
        int y = 0;

        try {
            double result = divide(x, y);
            System.out.println("Результат: " + result);
        } catch (CustomDivisionException ex) {
            System.err.println("Поймано CustomDivisionException: " + ex.getMessage());
            logException(ex);
        } catch (Exception ex) {
            System.err.println("Поймано другое исключение: " + ex.getMessage());
            logException(ex);
        }
    }
}