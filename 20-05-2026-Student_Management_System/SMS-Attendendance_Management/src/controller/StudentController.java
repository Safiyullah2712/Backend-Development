package controller;

import model.Student;
import service.StudentService;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentController {

    private StudentService service = new StudentService();

    Scanner sc = new Scanner(System.in);

    public void start() {

        int choice;

        do {

            System.out.println("\n===== Student Management System =====");

            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Find Topper");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch(choice) {

                case 1:

                    addStudent();

                    break;

                case 2:

                    viewStudents();

                    break;

                case 3:

                    searchStudent();

                    break;

                case 4:

                    deleteStudent();

                    break;

                case 5:

                    showTopper();

                    break;

                case 6:

                    System.out.println("Application Closed.");

                    break;

                default:

                    System.out.println("Invalid Choice.");
            }

        } while(choice != 6);
    }

    // Add Student
    private void addStudent() {

        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Marks: ");
        double marks = sc.nextDouble();

        Student student = new Student(id, name, marks);

        String message = service.addStudent(student);

        System.out.println(message);
    }

    // View Students
    private void viewStudents() {

        ArrayList<Student> students = service.getAllStudents();

        if(students.isEmpty()) {

            System.out.println("No student records found.");

            return;
        }

        for(Student s : students) {

            System.out.println(s);
        }
    }

    // Search Student
    private void searchStudent() {

        System.out.print("Enter Student ID: ");

        int id = sc.nextInt();

        Student student = service.getStudentById(id);

        if(student != null) {

            System.out.println(student);
        }
        else {

            System.out.println("Student not found.");
        }
    }

    // Delete Student
    private void deleteStudent() {

        System.out.print("Enter Student ID: ");

        int id = sc.nextInt();

        String message = service.deleteStudent(id);

        System.out.println(message);
    }

    // Find Topper
    private void showTopper() {

        Student topper = service.getTopper();

        if(topper != null) {

            System.out.println("Topper Details:");
            System.out.println(topper);
        }
        else {

            System.out.println("No student data available.");
        }
    }
}