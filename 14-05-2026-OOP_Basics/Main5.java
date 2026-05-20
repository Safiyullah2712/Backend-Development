
abstract class Car {

    abstract void start();
    abstract void drive();

    public void stop() {
        System.out.println("Car stopped");
    }
}

class Tesla extends Car {

    void start() {
        System.out.println("Tesla starts silently");
    }

    void drive() {
        System.out.println("Tesla is driving automatically");
    }
}

class BMW extends Car {

    void start() {
        System.out.println("BMW engine starts with sound");
    }

    void drive() {
        System.out.println("BMW is driving manually");
    }
}


public class Main5 {
    public static void main(String[] args) {

        Car c1 = new Tesla();
        c1.start();
        c1.drive();
        c1.stop();

        System.out.println();

        Car c2 = new BMW();
        c2.start();
        c2.drive();
        c2.stop();
    }
}
