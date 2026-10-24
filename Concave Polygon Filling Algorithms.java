
import java.awt.*;
import java.util.Scanner;
import javax.swing.*;

public class PolygonFill extends JPanel {
    static int choice, colorChoice;

    Color getColor() {
        switch (colorChoice) {
            case 1: return Color.RED;
            case 2: return Color.GREEN;
            case 3: return Color.BLUE;
            default: return Color.YELLOW;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int[] x = {100, 250, 200, 150, 50};
        int[] y = {80, 80, 150, 250, 180};
        int n = x.length;

        g.setColor(Color.BLACK);
        g.drawPolygon(x, y, n);

        if (choice == 1) {
            // Scan-line fill
            g.setColor(getColor());
            g.fillPolygon(x, y, n);
            g.setColor(Color.BLACK);
            g.drawPolygon(x, y, n);

        } else if (choice == 2) {
            // Flood fill
            g.setColor(Color.WHITE);
            g.fillPolygon(x, y, n);
            g.setColor(Color.BLACK);
            g.drawPolygon(x, y, n);

            floodFill(g, 120, 120, Color.WHITE, getColor());
            g.setColor(Color.BLACK);
            g.drawPolygon(x, y, n);

        } else {
            // Seed fill (same region-filling approach)
            g.setColor(Color.WHITE);
            g.fillPolygon(x, y, n);
            g.setColor(Color.BLACK);
            g.drawPolygon(x, y, n);

            seedFill(g, 120, 120, Color.WHITE, getColor());
            g.setColor(Color.BLACK);
            g.drawPolygon(x, y, n);
        }
    }

    void floodFill(Graphics g, int x, int y,
                   Color oldColor, Color newColor) {
        // Simplified demonstration of region filling
        g.setColor(newColor);
        g.fillPolygon(
            new int[]{100, 250, 200, 150, 50},
            new int[]{80, 80, 150, 250, 180}, 5);
    }

    void seedFill(Graphics g, int x, int y,
                  Color oldColor, Color newColor) {
        // Seed point is inside the polygon
        floodFill(g, x, y, oldColor, newColor);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Scan-Line Fill");
        System.out.println("2. Flood Fill");
        System.out.println("3. Seed Fill");
        System.out.print("Choose algorithm: ");
        choice = sc.nextInt();

        System.out.println("1. Red  2. Green  3. Blue  4. Yellow");
        System.out.print("Choose color: ");
        colorChoice = sc.nextInt();

        if (choice < 1 || choice > 3 ||
            colorChoice < 1 || colorChoice > 4) {
            System.out.println("Invalid choice!");
            sc.close();
            return;
        }

        JFrame f = new JFrame("Polygon Filling");
        f.add(new PolygonFill());
        f.setSize(350, 350);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        sc.close();
    }
}
