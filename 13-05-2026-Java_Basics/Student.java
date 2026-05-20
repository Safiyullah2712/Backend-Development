class Student {

    // Instance variables
    int id;
    String name;
    double marks;

    // Constructor
    Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    // Method to display details
    void displayDetails() {
        System.out.println("Student ID   : " + id);
        System.out.println("Student Name : " + name);
        System.out.println("Marks        : " + marks);
        System.out.println("---------------------------");
    }

    // Main method
    public static void main(String[] args) {

        // Creating objects
        Student s1 = new Student(101, "Arun", 87.5);
        Student s2 = new Student(102, "Priya", 92.0);

        // Display details
        s1.displayDetails();
        s2.displayDetails();
    }
}