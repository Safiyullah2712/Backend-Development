package controller;

import model.Attendance;
import service.AttendanceService;

import java.util.ArrayList;
import java.util.Scanner;

public class AttendanceController {

    private AttendanceService service =
            new AttendanceService();

    private Scanner sc = new Scanner(System.in);

    //Attendance Menu
    public void attendanceMenu() {

        int choice;

        do {

            System.out.println("\n===== Attendance Menu =====");

            System.out.println("1. Mark Attendance");
            System.out.println("2. View Attendance");
            System.out.println("3. Attendance Percentage");
            System.out.println("4. Back");

            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch(choice) {

                case 1:

                    markAttendance();

                    break;

                case 2:

                    viewAttendance();

                    break;

                case 3:

                    attendancePercentage();

                    break;

                case 4:

                    System.out.println("Returning...");

                    break;

                default:

                    System.out.println("Invalid choice.");
            }

        } while(choice != 4);
    }

    //Mark Attendance
    private void markAttendance() {

        System.out.print("Enter Attendance ID: ");
        int attendanceId = sc.nextInt();

        System.out.print("Enter Student ID: ");
        int studentId = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Date: ");
        String date = sc.nextLine();

        System.out.print("Enter Status (Present/Absent): ");
        String status = sc.nextLine();

        Attendance attendance =
                new Attendance(
                        attendanceId,
                        studentId,
                        date,
                        status
                );

        String result =
                service.markAttendance(attendance);

        System.out.println(result);
    }

    //View Attendance
    private void viewAttendance() {

        System.out.print("Enter Student ID: ");

        int studentId = sc.nextInt();

        ArrayList<Attendance> records =
                service.getAttendanceByStudent(studentId);

        if(records.isEmpty()) {

            System.out.println(
                    "No attendance records found."
            );

            return;
        }

        for(Attendance a : records) {

            System.out.println(a);
        }
    }

    //Attendance Percentage
    private void attendancePercentage() {

        System.out.print("Enter Student ID: ");

        int studentId = sc.nextInt();

        double percentage =
                service.calculateAttendancePercentage(
                        studentId
                );

        System.out.println(
                "Attendance Percentage: "
                        + percentage + "%"
        );
    }
}