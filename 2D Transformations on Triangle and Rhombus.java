
import java.awt.*;
import java.util.Scanner;
import javax.swing.*;

public class TransformShapes extends JPanel {
    static int choice;
    static double a, b, angle;

    int[] tx = {150, 200, 100};
    int[] ty = {100, 187, 187};

    int[] rx = {300, 350, 400, 350};
    int[] ry = {150, 100, 150, 200};

    void transform(Graphics g, int[] x, int[] y, int n,
                    Color color) {
        int[] nx = new int[n];
        int[] ny = new int[n];

        for (int i = 0; i < n; i++) {
            double X = x[i], Y = y[i];

            switch (choice) {
                case 1: // Translation
                    X += a;
                    Y += b;
                    break;

                case 2: // Scaling
                    X *= a;
                    Y *= b;
                    break;

                case 3: // Rotation
                    double r = Math.toRadians(angle);
                    double temp = X * Math.cos(r) - Y * Math.sin(r);
                    Y = X * Math.sin(r) + Y * Math.cos(r);
                    X = temp;
                    break;

                case 4: // Shearing
                    double tempX = X + a * Y;
                    Y = Y + b * X;
                    X = tempX;
                    break;
            }

            nx[i] = (int) Math.round(X);
            ny[i] = (int) Math.round(Y);
        }

        g.setColor(color);
        g.drawPolygon(nx, ny, n);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawString("Original Triangle", 70, 30);
        g.drawString("Original Rhombus", 300, 30);

        g.setColor(Color.BLACK);
        g.drawPolygon(tx, ty, 3);
        g.drawPolygon(rx, ry, 4);

        transform(g, tx, ty, 3, Color.RED);
        transform(g, rx, ry, 4, Color.BLUE);

        g.setColor(Color.RED);
        g.drawString("Transformed Triangle", 40, 250);
        g.setColor(Color.BLUE);
        g.drawString("Transformed Rhombus", 280, 250);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Translation");
        System.out.println("2. Scaling");
        System.out.println("3. Rotation");
        System.out.println("4. Shearing");
        System.out.print("Enter choice: ");
        choice = sc.nextInt();

        if (choice == 1 || choice == 4) {
            System.out.print("Enter tx/shx: ");
            a = sc.nextDouble();
            System.out.print("Enter ty/shy: ");
            b = sc.nextDouble();
        } else if (choice == 2) {
            System.out.print("Enter sx: ");
            a = sc.nextDouble();
            System.out.print("Enter sy: ");
            b = sc.nextDouble();
        } else if (choice == 3) {
            System.out.print("Enter angle: ");
            angle = sc.nextDouble();
        } else {
            System.out.println("Invalid choice!");
            sc.close();
            return;
        }

        JFrame f = new JFrame("2D Transformations");
        f.add(new TransformShapes());
        f.setSize(500, 320);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        sc.close();
    }
}
