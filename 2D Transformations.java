
import java.util.Scanner;

public class Transform2D {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double x, y, a, b, angle, rad;
        double nx, ny;

        System.out.print("Enter point (x y): ");
        x = sc.nextDouble();
        y = sc.nextDouble();

        System.out.println("1. Translation");
        System.out.println("2. Scaling");
        System.out.println("3. Rotation");
        System.out.println("4. Shearing");
        System.out.print("Enter choice: ");
        int ch = sc.nextInt();

        switch (ch) {
            case 1:
                System.out.print("Enter tx and ty: ");
                a = sc.nextDouble();
                b = sc.nextDouble();
                nx = x + a;
                ny = y + b;
                break;

            case 2:
                System.out.print("Enter sx and sy: ");
                a = sc.nextDouble();
                b = sc.nextDouble();
                nx = x * a;
                ny = y * b;
                break;

            case 3:
                System.out.print("Enter angle in degrees: ");
                angle = sc.nextDouble();
                rad = Math.toRadians(angle);
                nx = x * Math.cos(rad) - y * Math.sin(rad);
                ny = x * Math.sin(rad) + y * Math.cos(rad);
                break;

            case 4:
                System.out.print("Enter shx and shy: ");
                a = sc.nextDouble();
                b = sc.nextDouble();
                nx = x + a * y;
                ny = y + b * x;
                break;

            default:
                System.out.println("Invalid choice!");
                sc.close();
                return;
        }

        System.out.printf("Original point: (%.2f, %.2f)%n", x, y);
        System.out.printf("Transformed point: (%.2f, %.2f)%n", nx, ny);

        sc.close();
    }
}
