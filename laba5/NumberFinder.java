import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;
public class NumberFinder {
    public static void main(String[] args) {
        String text = "Целое число: 10, дробное число 12.17, отрицательное число -7";
        try {
            Pattern pattern = Pattern.compile("-?\\d+\\.\\d+|-?\\d+");
            Matcher matcher = pattern.matcher(text);
            boolean foundAny = false;
            while (matcher.find()) {
                System.out.println(matcher.group());
                foundAny = true;
            }
            if (!foundAny) {
                System.out.println("Чисел в тексте не найдено.");
            }
        } catch (PatternSyntaxException e) {
            System.err.println("Regex syntax error: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Input text is null.");
        }
    }
}