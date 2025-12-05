import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
public class IPChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите айпи адрес: ");
        String ip = scanner.nextLine();
        try {
            String regex = "\\b(?:(?:25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)\\.){3}" +
                           "(?:25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)\\b";
            Pattern pattern = Pattern.compile(regex);
            if (pattern.matcher(ip).matches()) {
                System.out.println("Верный айпи адрес.");
            } else {
                System.out.println("Неверный айпи адрес.");
            }
        } catch (PatternSyntaxException e) {
            System.err.println("Regex syntax error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}