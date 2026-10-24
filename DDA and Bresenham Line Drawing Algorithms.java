
import javax.swing.*;
import java.awt.*;

public class LinePatterns extends JPanel {

    // DDA line drawing algorithm
    void drawDDA(Graphics g, int x1, int y1, int x2, int y2,
                  String style) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xInc = (float) dx / steps;
        float yInc = (float) dy / steps;
        float x = x1, y = y1;

        for (int i = 0; i <= steps; i++) {
            if (style.equals("dotted")) {
                if (i % 4 == 0)
                    g.fillOval(Math.round(x), Math.round(y), 2, 2);
            } else {
                g.fillRect(Math.round(x), Math.round(y), 4, 4);
            }

            x += xInc;
            y += yInc;
        }
    }

    // Bresenham line drawing algorithm
    void drawBresenham(Graphics g, int x1, int y1,
                       int x2, int y2, String style) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;
        int count = 0;

        while (true) {
            if (style.equals("solid") || count % 10 < 6) {
                g.fillRect(x1, y1, 3, 3);
            }

            if (x1 == x2 && y1 == y2)
                break;

            int e2 = 2 * err;

            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }

            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }

            count++;
        }
    }

    void rectangle(Graphics g, int x, int y, int w, int h,
                   String style) {
        drawDDA(g, x, y, x + w, y, style);
        drawDDA(g, x + w, y, x + w, y + h, style);
        drawDDA(g, x + w, y + h, x, y + h, style);
        drawDDA(g, x, y + h, x, y, style);
    }

    void diamond(Graphics g, int cx, int cy, int r,
                 String style) {
        int topX = cx, topY = cy - r;
        int rightX = cx + r, rightY = cy;
        int bottomX = cx, bottomY = cy + r;
        int leftX = cx - r, leftY = cy;

        drawBresenham(g, topX, topY, rightX, rightY, style);
        drawBresenham(g, rightX, rightY, bottomX, bottomY, style);
        drawBresenham(g, bottomX, bottomY, leftX, leftY, style);
        drawBresenham(g, leftX, leftY, topX, topY, style);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        g.drawString("DDA - Dotted Rectangle", 50, 30);
        rectangle(g, 50, 50, 150, 90, "dotted");

        g.drawString("DDA - Thick Rectangle", 280, 30);
        rectangle(g, 280, 50, 150, 90, "thick");

        g.drawString("Bresenham - Dashed Diamond", 50, 200);
        diamond(g, 130, 300, 70, "dashed");

        g.drawString("Bresenham - Solid Diamond", 280, 200);
        diamond(g, 355, 300, 70, "solid");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Line Drawing Algorithms");
        frame.add(new LinePatterns());
        frame.setSize(500, 430);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
