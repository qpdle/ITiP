package org.example;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) { //ри выводе логов будет видно из какого они класса
        logger.info("The program is running");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the line: ");
        String input = scanner.nextLine();
        String result = StringUtils.reverse(input);
        logger.info("Result: {}", result);
        logger.info("The program is completed");
    }
}