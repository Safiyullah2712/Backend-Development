package main;

import controller.AttendanceController;
import controller.StudentController;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        StudentController studentController =
                new StudentController();

        AttendanceController attendanceController =
                new AttendanceController();

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n===== Main Menu =====");

            System.out.println("1. Student Module");
            System.out.println("2. Attendance Module");
            System.out.println("3. Exit");

            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch(choice) {

                case 1:

                    studentController.start();

                    break;

                case 2:

                    attendanceController.attendanceMenu();

                    break;

                case 3:

                    System.out.println("Application Closed.");

                    break;

                default:

                    System.out.println("Invalid Choice.");
            }

        } while(choice != 3);
    }
}