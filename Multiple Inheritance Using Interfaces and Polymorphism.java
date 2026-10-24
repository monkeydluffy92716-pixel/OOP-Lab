
interface Printable {
    void print();
}

interface Showable {
    void show();
}

// Implementing multiple interfaces
class Demo implements Printable, Showable {

    public void print() {
        System.out.println("Printing data...");
    }

    public void show() {
        System.out.println("Showing data...");
    }
}

public class Main {
    public static void main(String[] args) {
        Demo d = new Demo();

        // Polymorphism using interface references
        Printable p = d;
        Showable s = d;

        p.print();
        s.show();
    }
}
