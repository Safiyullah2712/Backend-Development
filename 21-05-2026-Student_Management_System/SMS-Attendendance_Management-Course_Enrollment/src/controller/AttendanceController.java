package controller;

import model.Attendance;
import service.AttendanceService;

import java.util.Scanner;
import java.util.logging.Logger;

public class AttendanceController {

    private static final Logger logger =
            Logger.getLogger(AttendanceController.class.getName());

    private AttendanceService service =
            new AttendanceService();

    private Scanner sc = new Scanner(System.in);

    public void start() {

        int choice;

        do {

            logger.info("===== Attendance Menu =====");

            logger.info("1. Mark Attendance");
            logger.info("2. View Attendance");
            logger.info("3. Exit");

            choice = sc.nextInt();

            switch(choice) {

                case 1:
                    markAttendance();
                    break;

                case 2:
                    viewAttendance();
                    break;

                case 3:
                    logger.info("Returning...");
                    break;

                default:
                    logger.warning("Invalid Choice");
            }

        } while(choice != 3);
    }
    
    private void markAttendance() {

        logger.info("Enter Attendance ID:");
        int attendanceId = sc.nextInt();

        logger.info("Enter Student ID:");
        int studentId = sc.nextInt();

        sc.nextLine();

        logger.info("Enter Date:");
        String date = sc.nextLine();

        logger.info("Enter Status:");
        String status = sc.nextLine();

        Attendance attendance =
                new Attendance(attendanceId,
                        studentId,
                        date,
                        status);

        logger.info(service.markAttendance(attendance));
    }

    private void viewAttendance() {

        logger.info("Enter Student ID:");

        int studentId = sc.nextInt();

        for(Attendance a :
                service.getAttendanceByStudent(studentId)) {

            logger.info(a.toString());
        }
    }
}