package controller;

import model.Enrollment;
import service.EnrollmentService;

import java.util.Scanner;
import java.util.logging.Logger;

public class EnrollmentController {

    private static final Logger logger =
            Logger.getLogger(EnrollmentController.class.getName());

    private EnrollmentService service =
            new EnrollmentService();

    private Scanner sc = new Scanner(System.in);

    public void enrollStudent() {

        logger.info("Enter Enrollment ID:");
        int enrollmentId = sc.nextInt();

        logger.info("Enter Student ID:");
        int studentId = sc.nextInt();

        logger.info("Enter Course ID:");
        int courseId = sc.nextInt();

        Enrollment enrollment =
                new Enrollment(enrollmentId,
                        studentId,
                        courseId);

        logger.info(service.enrollStudent(enrollment));
    }
}