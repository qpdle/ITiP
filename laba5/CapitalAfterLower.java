import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;
public class CapitalAfterLower {
    public static void main(String[] args) {
        String text = "aaaDCv vbn NN vb-bb";
        try {
            Pattern pattern = Pattern.compile("([a-z])([A-Z])");
            Matcher matcher = pattern.matcher(text);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                matcher.appendReplacement(sb, "!" + matcher.group(1) + matcher.group(2) + "!");
            }
            matcher.appendTail(sb);
            System.out.println(sb.toString());
        } catch (PatternSyntaxException e) {
            System.err.println("Regex syntax error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}