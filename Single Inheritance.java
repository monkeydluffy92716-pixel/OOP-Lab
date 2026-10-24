
class Animal {
    void eat() {
        System.out.println("Animal is eating.");
    }

    void sleep() {
        System.out.println("Animal is sleeping.");
    }
}

// Subclass extends superclass
class Dog extends Animal {
    void bark() {
        System.out.println("Dog is barking.");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog d = new Dog();

        d.eat();    // Inherited method
        d.sleep();  // Inherited method
        d.bark();   // Subclass method
    }
}
