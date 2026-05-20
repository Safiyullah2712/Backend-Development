class Employee {

    // Parent class variables
    int empId;
    String name;
    double salary;

    // Parent class constructor
    Employee(int empId, String name, double salary) {
        this.empId = empId;
        this.name = name;
        this.salary = salary;
    }

    // Parent class method
    void displayEmployee() {
        System.out.println("Employee ID : " + empId);
        System.out.println("Name        : " + name);
        System.out.println("Salary      : " + salary);
    }
}

// Child class inheriting Employee
class Developer extends Employee {

    String programmingLanguage;

    // Child class constructor
    Developer(int empId, String name, double salary,
              String programmingLanguage) {

        // Calling parent constructor
        super(empId, name, salary);

        this.programmingLanguage = programmingLanguage;
    }

    // Child class method
    void displayDeveloper() {

        // Calling parent method
        displayEmployee();

        System.out.println("Language    : " + programmingLanguage);
    }

    // Main method
    public static void main(String[] args) {

        Developer dev1 = new Developer(
                101,
                "Arun",
                75000,
                "Java"
        );

        System.out.println("=== Developer Details ===");

        dev1.displayDeveloper();
    }
}