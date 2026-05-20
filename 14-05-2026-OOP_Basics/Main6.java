class Car1{

    private double fuel = 5.0;
    private boolean engineOn = false;

    public void start() {
        if (fuel > 0) {
            engineOn = true;
            System.out.println("Car started…");
        } else {
            System.out.println("No fuel");
        }
    }

    public void drive() {
        if (engineOn && fuel > 0) {
            fuel--;
            System.out.println("Car is moving…");
        } else {
            System.out.println("Cannot drive");
        }
    }

    public void refuel(double amount) {
        fuel += amount;
        System.out.println("Fuel added");
    }

    public void show() {
        System.out.println("Fuel: " + fuel);
        System.out.println("Engine: " + engineOn);
    }
}

public class Main6 {
    public static void main(String[] args) {

        Car1 car = new Car1();

        car.start();
        car.drive();

        car.refuel(3);
        car.drive();

        car.show();
    }
}