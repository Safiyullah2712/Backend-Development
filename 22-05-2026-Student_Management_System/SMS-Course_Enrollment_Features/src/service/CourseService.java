package service;

import model.Course;

import repository.CourseRepository;
import repository.EnrollmentRepository;

public class CourseService {

    private CourseRepository repository =
            new CourseRepository();

    private EnrollmentRepository enrollmentRepo =
            new EnrollmentRepository();

    //Add Course
    public String addCourse(Course course) {

        if(course.getCourseName().isEmpty()) {

            return "Course name cannot be empty.";
        }

        if(course.getInstructorName().length() < 3) {

            return "Instructor name minimum 3 characters.";
        }

        repository.save(course);

        return "Course added successfully.";
    }

    //Update Course
    public String updateCourse(
            int courseId,
            String courseName,
            String instructorName) {

        Course course =
                repository.findById(courseId);

        if(course == null) {

            return "Course not found.";
        }

        Course updatedCourse =
                new Course(
                        courseId,
                        courseName,
                        instructorName
                );

        repository.save(updatedCourse);

        return "Course updated successfully.";
    }

    //Delete Course
    public String deleteCourse(int courseId) {

        boolean deleted =
                repository.deleteCourse(courseId);

        if(deleted) {

            enrollmentRepo.removeByCourseId(courseId);

            return "Course deleted successfully.";
        }

        return "Course not found.";
    }

    //Search by Instructor
    public void searchByInstructor(
            String instructorName) {

        boolean found = false;

        for(Course course :
                repository.findAll()) {

            if(course.getInstructorName()

                    .equalsIgnoreCase(
                            instructorName
                    )) {

                System.out.println(course);

                found = true;
            }
        }

        if(!found) {

            System.out.println(
                    "No courses found."
            );
        }
    }

    //Courses With No Students
    public void coursesWithoutStudents() {

        for(Course course :
                repository.findAll()) {

            boolean enrolled = false;

            for(var enrollment :
                    enrollmentRepo.findAll()) {

                if(enrollment.getCourseId()
                        == course.getCourseId()) {

                    enrolled = true;

                    break;
                }
            }

            if(!enrolled) {

                System.out.println(course);
            }
        }
    }
}