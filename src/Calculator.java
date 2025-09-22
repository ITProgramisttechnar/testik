import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        scanner.close();

        try {
            int result = calculate(input);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            System.exit(1);
        }
    }
    public static int calculate(String input) throws Exception {

        Pattern pattern = Pattern.compile("^\\s*(\\d+)\\s*([+\\-*/])\\s*(\\d+)\\s*$");
        Matcher matcher = pattern.matcher(input);

        if (!matcher.find()) {
            throw new Exception("Неверный формат ввода. Ожидается: число оператор число");
        }


        int a = Integer.parseInt(matcher.group(1));
        String operator = matcher.group(2);
        int b = Integer.parseInt(matcher.group(3));


        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new Exception("Числа должны быть от 1 до 10 включительно");
        }


        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0) {
                    throw new Exception("Деление на ноль не допускается");
                }
                yield a / b;
            }
            default -> throw new Exception("Неизвестная операция: " + operator);
        };
    }
}