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

            logger.info("===== Main Menu =====");

            logger.info("1. Student Module");
            logger.info("2. Attendance Module");
            logger.info("3. Add Course");
            logger.info("4. Enroll Student");
            logger.info("5. Exit");

            choice = sc.nextInt();

            switch(choice) {

                case 1:
                    studentController.start();
                    break;

                case 2:
                    attendanceController.start();
                    break;

                case 3:
                    courseController.addCourse();
                    break;

                case 4:
                    enrollmentController.enrollStudent();
                    break;

                case 5:
                    logger.info("Application Closed.");
                    break;

                default:
                    logger.warning("Invalid Choice");
            }

        } while(choice != 5);
    }
}