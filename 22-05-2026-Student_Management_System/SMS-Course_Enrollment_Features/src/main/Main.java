package main;

import controller.AttendanceController;
import controller.CourseController;
import controller.EnrollmentController;
import controller.StudentController;

import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger =
            Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        StudentController studentController =
                new StudentController();

        AttendanceController attendanceController =
                new AttendanceController();

        CourseController courseController =
                new CourseController();

        EnrollmentController enrollmentController =
                new EnrollmentController();

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            logger.info(
                    "\n===== Student Management System ====="
            );

            logger.info("1. Student Module");

            logger.info("2. Attendance Module");

            logger.info("3. Course Module");

            logger.info("4. Enrollment Module");

            logger.info("5. Exit");

            logger.info("Enter Choice:");

            choice = sc.nextInt();

            switch(choice) {

                case 1:

                    studentController.start();

                    break;

                case 2:

                    attendanceController.start();

                    break;

                case 3:

                    courseController.start();

                    break;

                case 4:

                    enrollmentController.start();

                    break;

                case 5:

                    logger.info(
                            "Application Closed Successfully."
                    );

                    break;

                default:

                    logger.warning("Invalid Choice");
            }

        } while(choice != 5);
    }
}