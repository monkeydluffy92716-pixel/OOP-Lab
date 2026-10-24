
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            try {
                System.out.println("\n--- Java Calculator ---");
                System.out.println("1. Addition (+)");
                System.out.println("2. Subtraction (-)");
                System.out.println("3. Multiplication (*)");
                System.out.println("4. Division (/)");
                System.out.println("5. Modulus (%)");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");

                int choice = Integer.parseInt(sc.nextLine());

                if (choice == 6) {
                    running = false;
                    System.out.println("Calculator closed.");
                    continue;
                }

                if (choice < 1 || choice > 6) {
                    System.out.println("Invalid choice! Try again.");
                    continue;
                }

                System.out.print("Enter first number: ");
                double a = Double.parseDouble(sc.nextLine());

                System.out.print("Enter second number: ");
                double b = Double.parseDouble(sc.nextLine());

                double result;

                switch (choice) {
                    case 1:
                        result = a + b;
                        break;
                    case 2:
                        result = a - b;
                        break;
                    case 3:
                        result = a * b;
                        break;
                    case 4:
                        if (b == 0) {
                            System.out.println(
                                "Error: Division by zero is not allowed.");
                            continue;
                        }
                        result = a / b;
                        break;
                    case 5:
                        if (b == 0) {
                            System.out.println(
                                "Error: Modulus by zero is not allowed.");
                            continue;
                        }
                        result = a % b;
                        break;
                    default:
                        continue;
                }

                if (Double.isFinite(result)) {
                    System.out.println("Result = " + result);
                } else {
                    System.out.println(
                        "Error: Result is outside the supported range.");
                }

            } catch (NumberFormatException e) {
                System.out.println(
                    "Invalid input! Please enter valid numbers.");
            }
        }

        sc.close();
    }
}
