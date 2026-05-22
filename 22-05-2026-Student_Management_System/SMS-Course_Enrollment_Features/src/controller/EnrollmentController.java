package controller;

import model.Enrollment;

import service.EnrollmentService;

import java.util.Scanner;
import java.util.logging.Logger;

public class EnrollmentController {

    private static final Logger logger =
            Logger.getLogger(
                    EnrollmentController.class.getName()
            );

    private EnrollmentService service =
            new EnrollmentService();

    private Scanner sc = new Scanner(System.in);

    public void start() {

        int choice;

        do {

            logger.info("\n===== Enrollment Menu =====");

            logger.info("1. Enroll Student");
            logger.info("2. Unenroll Student");
            logger.info("3. View Courses of Student");
            logger.info("4. View Students in Course");
            logger.info("5. Students in Multiple Courses");
            logger.info("6. Count Students Per Course");
            logger.info("7. Top 3 Popular Courses");
            logger.info("8. Exit");

            choice = sc.nextInt();

            switch(choice) {

                case 1:

                    enrollStudent();

                    break;

                case 2:

                    unenrollStudent();

                    break;

                case 3:

                    viewCoursesOfStudent();

                    break;

                case 4:

                    viewStudentsInCourse();

                    break;

                case 5:

                    service.studentsInMultipleCourses();

                    break;

                case 6:

                    service.countStudentsPerCourse();

                    break;

                case 7:

                    service.top3Courses();

                    break;

                case 8:

                    logger.info("Returning...");

                    break;

                default:

                    logger.warning("Invalid Choice");
            }

        } while(choice != 8);
    }

    private void enrollStudent() {

        logger.info("Enter Enrollment ID:");
        int enrollmentId = sc.nextInt();

        logger.info("Enter Student ID:");
        int studentId = sc.nextInt();

        logger.info("Enter Course ID:");
        int courseId = sc.nextInt();

        Enrollment enrollment =
                new Enrollment(
                        enrollmentId,
                        studentId,
                        courseId
                );

        logger.info(
                service.enrollStudent(enrollment)
        );
    }

    private void unenrollStudent() {

        logger.info("Enter Student ID:");
        int studentId = sc.nextInt();

        logger.info("Enter Course ID:");
        int courseId = sc.nextInt();

        logger.info(
                service.unenrollStudent(
                        studentId,
                        courseId
                )
        );
    }

    private void viewCoursesOfStudent() {

        logger.info("Enter Student ID:");

        int studentId = sc.nextInt();

        service.viewCoursesOfStudent(studentId);
    }

    private void viewStudentsInCourse() {

        logger.info("Enter Course ID:");

        int courseId = sc.nextInt();

        service.viewStudentsInCourse(courseId);
    }
}