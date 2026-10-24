
import java.awt.*;
import java.util.Scanner;
import javax.swing.*;

public class CircleDrawing extends JPanel {
    static int algorithm = 1, style = 1;

    void plot(Graphics g, int x, int y, int cx, int cy) {
        if (style == 1 || (style == 2 && (x + y) % 4 == 0)
                || (style == 3 && (x + y) % 12 < 7)) {
            g.fillRect(cx + x, cy + y, 2, 2);
            g.fillRect(cx - x, cy + y, 2, 2);
            g.fillRect(cx + x, cy - y, 2, 2);
            g.fillRect(cx - x, cy - y, 2, 2);
            g.fillRect(cx + y, cy + x, 2, 2);
            g.fillRect(cx - y, cy + x, 2, 2);
            g.fillRect(cx + y, cy - x, 2, 2);
            g.fillRect(cx - y, cy - x, 2, 2);
        }
    }

    void drawCircle(Graphics g, int cx, int cy, int r) {
        int x = 0, y = r;

        if (algorithm == 1) { // DDA circle
            double angleStep = 1.0 / r;
            for (double a = 0; a < Math.PI / 4; a += angleStep) {
                x = (int) Math.round(r * Math.cos(a));
                y = (int) Math.round(r * Math.sin(a));
                plot(g, x, y, cx, cy);
            }
        } else {
            int d = 3 - 2 * r;

            while (x <= y) {
                plot(g, x, y, cx, cy);

                if (algorithm == 2) { // Bresenham
                    if (d < 0)
                        d += 4 * x + 6;
                    else {
                        d += 4 * (x - y) + 10;
                        y--;
                    }
                } else { // Midpoint
                    if (d < 0)
                        d += 2 * x + 3;
                    else {
                        d += 2 * (x - y) + 5;
                        y--;
                    }
                }
                x++;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        drawCircle(g, 200, 170, 100);
        g.drawString("Circle Drawing", 150, 30);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. DDA Algorithm");
        System.out.println("2. Bresenham Algorithm");
        System.out.println("3. Midpoint Algorithm");
        System.out.print("Choose algorithm: ");
        algorithm = sc.nextInt();

        System.out.println("1. Solid  2. Dotted  3. Dashed");
        System.out.print("Choose style: ");
        style = sc.nextInt();

        if (algorithm < 1 || algorithm > 3 ||
            style < 1 || style > 3) {
            System.out.println("Invalid choice!");
            sc.close();
            return;
        }

        JFrame f = new JFrame("Circle Drawing");
        f.add(new CircleDrawing());
        f.setSize(420, 380);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        sc.close();
    }
}
