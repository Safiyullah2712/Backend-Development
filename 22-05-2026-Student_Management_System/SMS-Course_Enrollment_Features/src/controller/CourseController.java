package controller;

import model.Course;

import service.CourseService;

import java.util.Scanner;
import java.util.logging.Logger;

public class CourseController {

    private static final Logger logger =
            Logger.getLogger(
                    CourseController.class.getName()
            );

    private CourseService service =
            new CourseService();

    private Scanner sc = new Scanner(System.in);

    public void start() {

        int choice;

        do {

            logger.info("\n===== Course Menu =====");

            logger.info("1. Add Course");
            logger.info("2. Update Course");
            logger.info("3. Delete Course");
            logger.info("4. Search by Instructor");
            logger.info("5. Courses With No Students");
            logger.info("6. Exit");

            choice = sc.nextInt();

            switch(choice) {

                case 1:

                    addCourse();

                    break;

                case 2:

                    updateCourse();

                    break;

                case 3:

                    deleteCourse();

                    break;

                case 4:

                    searchByInstructor();

                    break;

                case 5:

                    service.coursesWithoutStudents();

                    break;

                case 6:

                    logger.info("Returning...");

                    break;

                default:

                    logger.warning("Invalid Choice");
            }

        } while(choice != 6);
    }

    private void addCourse() {

        logger.info("Enter Course ID:");
        int id = sc.nextInt();

        sc.nextLine();

        logger.info("Enter Course Name:");
        String name = sc.nextLine();

        logger.info("Enter Instructor Name:");
        String instructor = sc.nextLine();

        Course course =
                new Course(id, name, instructor);

        logger.info(
                service.addCourse(course)
        );
    }

    private void updateCourse() {

        logger.info("Enter Course ID:");
        int id = sc.nextInt();

        sc.nextLine();

        logger.info("Enter New Course Name:");
        String name = sc.nextLine();

        logger.info("Enter New Instructor Name:");
        String instructor = sc.nextLine();

        logger.info(
                service.updateCourse(
                        id,
                        name,
                        instructor
                )
        );
    }

    private void deleteCourse() {

        logger.info("Enter Course ID:");

        int id = sc.nextInt();

        logger.info(
                service.deleteCourse(id)
        );
    }

    private void searchByInstructor() {

        sc.nextLine();

        logger.info("Enter Instructor Name:");

        String instructor =
                sc.nextLine();

        service.searchByInstructor(instructor);
    }
}