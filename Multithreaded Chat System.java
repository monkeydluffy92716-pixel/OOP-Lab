
class ChatUser extends Thread {
    private final String userName;
    private volatile boolean running = true;
    private boolean paused = false;

    ChatUser(String name, int priority) {
        userName = name;
        setPriority(priority);
    }

    synchronized void pauseChat() {
        paused = true;
        System.out.println(userName + " chat paused.");
    }

    synchronized void resumeChat() {
        paused = false;
        notifyAll();
        System.out.println(userName + " chat resumed.");
    }

    void stopChat() {
        running = false;
        interrupt();
    }

    private synchronized void checkPause()
            throws InterruptedException {
        while (paused && running) {
            wait();
        }
    }

    public void run() {
        String[] messages = {
            "Hello!",
            "How are you?",
            "Meeting at 5 PM",
            "Goodbye!"
        };

        try {
            for (String message : messages) {
                checkPause();

                if (!running) {
                    break;
                }

                System.out.println(
                    userName + " sends: " + message);

                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            // Allows the thread to stop safely.
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(userName + " has left the chat.");
        }
    }
}

public class ChatApplication {
    public static void main(String[] args)
            throws InterruptedException {

        ChatUser user1 = new ChatUser(
            "Alice", Thread.NORM_PRIORITY);
        ChatUser user2 = new ChatUser(
            "Bob", Thread.MAX_PRIORITY);

        System.out.println("Starting chat...");

        user1.start();
        user2.start();

        System.out.println(
            "Alice alive: " + user1.isAlive());
        System.out.println(
            "Bob alive: " + user2.isAlive());

        // Demonstrate pause and resume.
        Thread.sleep(600);
        user1.pauseChat();

        Thread.sleep(1000);
        user1.resumeChat();

        // Demonstrate stopping a thread.
        Thread.sleep(700);
        user2.stopChat();

        // Wait for both threads to finish.
        user1.join();
        user2.join();

        System.out.println(
            "Alice alive: " + user1.isAlive());
        System.out.println(
            "Bob alive: " + user2.isAlive());

        System.out.println("Chat application closed.");
    }
}
