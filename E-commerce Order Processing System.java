
import java.util.Scanner;

class Product {
    int id;
    String name;
    double price;
    int quantity;

    // Default constructor
    Product() {
        id = 0;
        name = "Unknown";
        price = 0;
        quantity = 0;
    }

    // Parameterized constructor
    Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = 1;
    }

    // Overloaded constructor
    Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    double getTotal() {
        return price * quantity;
    }

    void display() {
        System.out.printf("%-6d %-15s %10.2f %8d %12.2f%n",
                id, name, price, quantity, getTotal());
    }
}

public class EcommerceOrder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Products initialized using constructors
        Product p1 = new Product(101, "Keyboard", 800, 2);
        Product p2 = new Product(102, "Mouse", 400);
        
        System.out.println("=== E-Commerce Order System ===");
        System.out.print("Enter your product name: ");
        String name = sc.nextLine();

        System.out.print("Enter product price: ");
        double price = sc.nextDouble();

        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();

        // Validate user input
        if (price <= 0 || quantity <= 0) {
            System.out.println("Invalid price or quantity!");
            sc.close();
            return;
        }

        Product p3 = new Product(103, name, price, quantity);

        double subtotal = p1.getTotal()
                        + p2.getTotal()
                        + p3.getTotal();

        // Discount policies
        double discountRate = 0;

        if (subtotal >= 5000) {
            discountRate = 0.15; // 15% discount
        } else if (subtotal >= 2000) {
            discountRate = 0.10; // 10% discount
        } else if (subtotal >= 1000) {
            discountRate = 0.05; // 5% discount
        }

        double discount = subtotal * discountRate;
        double finalAmount = subtotal - discount;

        // Invoice
        System.out.println("\n========== ORDER INVOICE ==========");
        System.out.printf("%-6s %-15s %10s %8s %12s%n",
                "ID", "Product", "Price", "Qty", "Total");
        System.out.println("-----------------------------------------------");

        p1.display();
        p2.display();
        p3.display();

        System.out.println("-----------------------------------------------");
        System.out.printf("Subtotal:          Rs. %.2f%n", subtotal);
        System.out.printf("Discount (%.0f%%):    Rs. %.2f%n",
                discountRate * 100, discount);
        System.out.printf("Final Amount:      Rs. %.2f%n", finalAmount);
        System.out.println("===================================");
        System.out.println("Thank you for shopping!");

        sc.close();
    }
}
