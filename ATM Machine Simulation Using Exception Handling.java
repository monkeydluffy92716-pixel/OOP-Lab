
import java.util.Scanner;

public class ATM {
    static double balance = 5000.0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\n===== ATM MACHINE =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            try {
                if (!sc.hasNextInt()) {
                    sc.nextLine();
                    throw new IllegalArgumentException(
                        "Please enter a valid menu number.");
                }

                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println(
                            "Current Balance: Rs. " + balance);
                        break;

                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdraw = readAmount(sc);

                        if (withdraw > balance) {
                            throw new ArithmeticException(
                                "Insufficient funds!");
                        }

                        balance -= withdraw;
                        System.out.println(
                            "Withdrawal successful!");
                        System.out.println(
                            "Remaining Balance: Rs. " + balance);
                        break;

                    case 3:
                        System.out.print("Enter amount to deposit: ");
                        double deposit = readAmount(sc);

                        balance += deposit;
                        System.out.println("Deposit successful!");
                        System.out.println(
                            "Updated Balance: Rs. " + balance);
                        break;

                    case 4:
                        System.out.println("Thank you for using ATM!");
                        break;

                    default:
                        throw new IllegalArgumentException(
                            "Invalid menu choice!");
                }

            } catch (ArithmeticException e) {
                System.out.println("Transaction Error: " + e.getMessage());

            } catch (IllegalArgumentException e) {
                System.out.println("Input Error: " + e.getMessage());

            } finally {
                System.out.println("Transaction process completed.");
            }

        } while (choice != 4);

        sc.close();
    }

    static double readAmount(Scanner sc) {
        if (!sc.hasNextDouble()) {
            sc.nextLine();
            throw new IllegalArgumentException(
                "Enter a valid numeric amount.");
        }

        double amount = sc.nextDouble();

        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException(
                "Amount must be a positive, finite number.");
        }

        return amount;
    }
}
