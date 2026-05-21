package controller;

import model.Course;
import repository.CourseRepository;

import java.util.Scanner;
import java.util.logging.Logger;

public class CourseController {

    private static final Logger logger =
            Logger.getLogger(CourseController.class.getName());

    private CourseRepository repository =
            new CourseRepository();

    private Scanner sc = new Scanner(System.in);

    public void addCourse() {

        logger.info("Enter Course ID:");
        int id = sc.nextInt();

        sc.nextLine();

        logger.info("Enter Course Name:");
        String name = sc.nextLine();

        logger.info("Enter Instructor Name:");
        String instructor = sc.nextLine();

        Course course =
                new Course(id, name, instructor);

        repository.save(course);

        logger.info("Course added successfully.");
    }
}