
import java.util.Scanner;

public class CohenSutherland {
    static final int INSIDE = 0;
    static final int LEFT = 1;
    static final int RIGHT = 2;
    static final int BOTTOM = 4;
    static final int TOP = 8;

    static double xmin, ymin, xmax, ymax;

    static int computeCode(double x, double y) {
        int code = INSIDE;

        if (x < xmin) code |= LEFT;
        else if (x > xmax) code |= RIGHT;

        if (y < ymin) code |= BOTTOM;
        else if (y > ymax) code |= TOP;

        return code;
    }

    static void clipLine(double x1, double y1,
                         double x2, double y2) {
        int code1 = computeCode(x1, y1);
        int code2 = computeCode(x2, y2);
        boolean accept = false;

        while (true) {
            if ((code1 | code2) == 0) {
                accept = true;
                break;
            } else if ((code1 & code2) != 0) {
                break;
            } else {
                int codeOut = (code1 != 0) ? code1 : code2;
                double x = 0, y = 0;

                if ((codeOut & TOP) != 0) {
                    x = x1 + (x2 - x1) *
                        (ymax - y1) / (y2 - y1);
                    y = ymax;
                } else if ((codeOut & BOTTOM) != 0) {
                    x = x1 + (x2 - x1) *
                        (ymin - y1) / (y2 - y1);
                    y = ymin;
                } else if ((codeOut & RIGHT) != 0) {
                    y = y1 + (y2 - y1) *
                        (xmax - x1) / (x2 - x1);
                    x = xmax;
                } else if ((codeOut & LEFT) != 0) {
                    y = y1 + (y2 - y1) *
                        (xmin - x1) / (x2 - x1);
                    x = xmin;
                }

                if (codeOut == code1) {
                    x1 = x;
                    y1 = y;
                    code1 = computeCode(x1, y1);
                } else {
                    x2 = x;
                    y2 = y;
                    code2 = computeCode(x2, y2);
                }
            }
        }

        if (accept) {
            System.out.println("Line accepted after clipping:");
            System.out.printf("(%.2f, %.2f) to (%.2f, %.2f)%n",
                              x1, y1, x2, y2);
        } else {
            System.out.println("Line rejected. Outside window.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter xmin ymin xmax ymax: ");
        xmin = sc.nextDouble();
        ymin = sc.nextDouble();
        xmax = sc.nextDouble();
        ymax = sc.nextDouble();

        if (xmin >= xmax || ymin >= ymax) {
            System.out.println("Invalid clipping window.");
            sc.close();
            return;
        }

        System.out.print("Enter line endpoints x1 y1 x2 y2: ");
        double x1 = sc.nextDouble();
        double y1 = sc.nextDouble();
        double x2 = sc.nextDouble();
        double y2 = sc.nextDouble();

        clipLine(x1, y1, x2, y2);
        sc.close();
    }
}
