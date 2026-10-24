
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class PolygonClipping extends JPanel {
    static ArrayList<Point> polygon = new ArrayList<>();
    static int xmin, ymin, xmax, ymax;

    static ArrayList<Point> clip(ArrayList<Point> input, int edge) {
        ArrayList<Point> output = new ArrayList<>();
        if (input.isEmpty()) return output;

        Point s = input.get(input.size() - 1);

        for (Point p : input) {
            boolean inP = inside(p, edge);
            boolean inS = inside(s, edge);

            if (inP) {
                if (!inS) output.add(intersect(s, p, edge));
                output.add(p);
            } else if (inS) {
                output.add(intersect(s, p, edge));
            }
            s = p;
        }
        return output;
    }

    static boolean inside(Point p, int edge) {
        switch (edge) {
            case 0: return p.x >= xmin; // Left
            case 1: return p.x <= xmax; // Right
            case 2: return p.y >= ymin; // Bottom
            default: return p.y <= ymax; // Top
        }
    }

    static Point intersect(Point a, Point b, int edge) {
        double x = a.x, y = a.y;
        double dx = b.x - a.x, dy = b.y - a.y;

        if (edge < 2) {
            x = (edge == 0) ? xmin : xmax;
            y = a.y + dy * (x - a.x) / dx;
        } else {
            y = (edge == 2) ? ymin : ymax;
            x = a.x + dx * (y - a.y) / dy;
        }
        return new Point((int)Math.round(x), (int)Math.round(y));
    }

    static ArrayList<Point> performClipping() {
        ArrayList<Point> result = new ArrayList<>(polygon);
        for (int edge = 0; edge < 4; edge++)
            result = clip(result, edge);
        return result;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLUE);
        g.drawRect(xmin, ymin, xmax - xmin, ymax - ymin);

        drawPolygon(g, polygon, Color.RED);

        ArrayList<Point> result = performClipping();
        drawPolygon(g, result, Color.GREEN);

        g.setColor(Color.BLACK);
        g.drawString("Red: Original Polygon", 20, 20);
        g.drawString("Green: Clipped Polygon", 20, 40);
        g.drawString("Blue: Clipping Window", 20, 60);
    }

    void drawPolygon(Graphics g, ArrayList<Point> points, Color color) {
        if (points.size() < 2) return;
        g.setColor(color);
        for (int i = 0; i < points.size(); i++) {
            Point a = points.get(i);
            Point b = points.get((i + 1) % points.size());
            g.drawLine(a.x, a.y, b.x, b.y);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of polygon vertices: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter x and y for vertex " + (i + 1) + ": ");
            polygon.add(new Point(sc.nextInt(), sc.nextInt()));
        }

        System.out.println("Enter clipping window (xmin ymin xmax ymax):");
        xmin = sc.nextInt();
        ymin = sc.nextInt();
        xmax = sc.nextInt();
        ymax = sc.nextInt();

        if (n < 3 || xmin >= xmax || ymin >= ymax) {
            System.out.println("Invalid polygon or clipping window.");
            sc.close();
            return;
        }

        JFrame frame = new JFrame("Sutherland-Hodgman Clipping");
        frame.add(new PolygonClipping());
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        sc.close();
    }
}
