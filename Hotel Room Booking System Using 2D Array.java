
import java.util.Scanner;

public class HotelBooking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 3 floors and 4 rooms on each floor
        int floors = 3;
        int rooms = 4;
        boolean[][] booked = new boolean[floors][rooms];

        int choice;

        do {
            System.out.println("\n--- Hotel Room Booking System ---");
            System.out.println("1. View Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Enter a number.");
                sc.nextLine();
                continue;
            }

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nRoom Status:");
                    System.out.println("A = Available, B = Booked");

                    for (int i = 0; i < floors; i++) {
                        System.out.print("Floor " + (i + 1) + ": ");

                        for (int j = 0; j < rooms; j++) {
                            System.out.print(
                                "Room " + (j + 1) + " [" +
                                (booked[i][j] ? "B" : "A") + "]  ");
                        }
                        System.out.println();
                    }
                    break;

                case 2:
                    System.out.print("Enter floor (1-3): ");
                    int floor = sc.nextInt();

                    System.out.print("Enter room number (1-4): ");
                    int room = sc.nextInt();

                    if (floor < 1 || floor > floors ||
                        room < 1 || room > rooms) {
                        System.out.println("Invalid floor or room number!");
                    } else if (booked[floor - 1][room - 1]) {
                        System.out.println("Room is already booked!");
                    } else {
                        booked[floor - 1][room - 1] = true;
                        System.out.println("Room booked successfully!");
                    }
                    break;

                case 3:
                    System.out.println("Thank you! Exiting system.");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 3);

        sc.close();
    }
}
