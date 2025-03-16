import java.util.*;
import java.util.regex.*;
import java.io.*;

public class Main {
    private static List<String> history = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Simple Console Calculator");
        System.out.println("Available operations: +, -, *, /, %, power, sqrt, abs, round");
        System.out.println("Type 'history' to view past calculations or 'exit' to quit.");

        while (true) {
            System.out.print("\nEnter an expression: ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("exit")) {
                System.out.println("Exiting Calculator. Goodbye!");
                break;
            }

            if (input.equals("history")) {
                printHistory();
                continue;
            }

            try {
                double result = evaluate(input);
                System.out.println("Result: " + result);
                history.add(input + " = " + result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    public static double evaluate(String input) throws Exception {
        input = input.replaceAll("\\s+", "");

        if (input.matches(".*power\\(.*\\).*")) {
            return power(parseFunction(input, "power"));
        } else if (input.matches(".*sqrt\\(.*\\).*")) {
            return sqrt(parseSingleArg(input, "sqrt"));
        } else if (input.matches(".*abs\\(.*\\).*")) {
            return abs(parseSingleArg(input, "abs"));
        } else if (input.matches(".*round\\(.*\\).*")) {
            return round(parseSingleArg(input, "round"));
        } else {
            return evaluateArithmetic(input);
        }
    }

    public static double evaluateArithmetic(String input) throws Exception {
        Pattern pattern = Pattern.compile("(-?\\d+(\\.\\d+)?)([+\\-*/%])(-?\\d+(\\.\\d+)?)");
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            throw new Exception("Invalid expression format.");
        }

        double num1 = Double.parseDouble(matcher.group(1));
        char operator = matcher.group(3).charAt(0);
        double num2 = Double.parseDouble(matcher.group(4));

        return switch (operator) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> {
                if (num2 == 0) throw new Exception("Cannot divide by zero.");
                yield num1 / num2;
            }
            case '%' -> {
                if (num2 == 0) throw new Exception("Cannot perform modulus by zero.");
                yield num1 % num2;
            }
            default -> throw new Exception("Unknown operator.");
        };
    }

    public static double power(double[] values) {
        return Math.pow(values[0], values[1]);
    }

    public static double sqrt(double value) throws Exception {
        if (value < 0) throw new Exception("Cannot calculate square root of a negative number.");
        return Math.sqrt(value);
    }

    public static double abs(double value) {
        return Math.abs(value);
    }

    public static double round(double value) {
        return Math.round(value);
    }

    public static double[] parseFunction(String input, String function) throws Exception {
        Pattern pattern = Pattern.compile(function + "\\((-?\\d+(\\.\\d+)?),(-?\\d+(\\.\\d+)?)\\)");
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) throw new Exception("Invalid function syntax.");
        return new double[]{Double.parseDouble(matcher.group(1)), Double.parseDouble(matcher.group(3))};
    }

    public static double parseSingleArg(String input, String function) throws Exception {
        Pattern pattern = Pattern.compile(function + "\\((-?\\d+(\\.\\d+)?)\\)");
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) throw new Exception("Invalid function syntax.");
        return Double.parseDouble(matcher.group(1));
    }

    public static void printHistory() {
        if (history.isEmpty()) {
            System.out.println("No calculations yet.");
        } else {
            System.out.println("\nCalculation History:");
            for (String record : history) {
                System.out.println(record);
            }
        }
    }
}