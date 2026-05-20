class Shape {

    // Overloaded methods
    double area(double side) {
        return side * side;
    }

    double area(double length, double breadth) {
        return length * breadth;
    }
}

// Child class
class Circle extends Shape {

    double area(int radius) {
        return 3.14 * radius * radius;
    }

    public static void main(String[] args) {

        Circle obj = new Circle();

        System.out.println("Square Area    : "
                + obj.area(5.0));

        System.out.println("Rectangle Area : "
                + obj.area(4.0, 6.0));

        System.out.println("Circle Area    : "
                + obj.area(7));
    }
}