import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
public class PasswordChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        try {
            String regex = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,16}$";
            Pattern pattern = Pattern.compile(regex);
            if (pattern.matcher(password).matches()) {
                System.out.println("Верный пароль.");
            } else {
                System.out.println("Неверный пароль. Пароль должен состоять из 8-16 символов, состоять из латинских букв, содержать как минимум одну букву и цифру.");
            }
        } catch (PatternSyntaxException e) {
            System.err.println("Regex syntax error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}