
import java.util.Scanner;

public class Rotation2D {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double x, y, angle, rad, xr, yr;
        double xp, yp;

        System.out.print("Enter point coordinates (x y): ");
        x = sc.nextDouble();
        y = sc.nextDouble();

        System.out.print("Enter rotation angle: ");
        angle = sc.nextDouble();

        rad = Math.toRadians(angle);

        // Rotation about X-axis (horizontal axis)
        xr = x;
        yr = y * Math.cos(rad);

        System.out.println("Rotation about X-axis: (" +
                           xr + ", " + yr + ")");

        // Rotation about an arbitrary point
        System.out.print("Enter arbitrary point (xp yp): ");
        xp = sc.nextDouble();
        yp = sc.nextDouble();

        xr = xp + (x - xp) * Math.cos(rad)
                - (y - yp) * Math.sin(rad);

        yr = yp + (x - xp) * Math.sin(rad)
                + (y - yp) * Math.cos(rad);

        System.out.println("Rotation about arbitrary point: (" +
                           xr + ", " + yr + ")");

        sc.close();
    }
}
