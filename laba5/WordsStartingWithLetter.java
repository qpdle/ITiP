import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;
public class WordsStartingWithLetter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите текст:");
        String text = scanner.nextLine();
        System.out.print("Введите букву, с которой будет начинаться слово: ");
        String letter = scanner.nextLine();
        try {
            if (letter == null || letter.isEmpty()) {
                System.out.println("Буква не введена.");
                return;
            }
            String escaped = Pattern.quote(letter.substring(0, 1));
            Pattern pattern = Pattern.compile("\\b" + escaped + "\\w*\\b", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(text);
            boolean foundAny = false;
            while (matcher.find()) {
                System.out.println(matcher.group());
                foundAny = true;
            }
            if (!foundAny) {
                System.out.println("Слов, начинающихся с '" + letter + "' не найдено.");
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