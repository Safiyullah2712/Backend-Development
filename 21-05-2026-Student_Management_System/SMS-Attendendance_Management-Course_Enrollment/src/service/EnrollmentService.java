package service;

import model.Course;
import model.Enrollment;
import model.Student;
import repository.CourseRepository;
import repository.EnrollmentRepository;
import repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentService {

    private EnrollmentRepository enrollmentRepo =
            new EnrollmentRepository();

    private StudentRepository studentRepo =
            new StudentRepository();

    private CourseRepository courseRepo =
            new CourseRepository();

    public String enrollStudent(Enrollment enrollment) {

        Student student =
                studentRepo.findById(
                        enrollment.getStudentId()
                );

        if(student == null) {
            return "Student not found.";
        }

        Course course =
                courseRepo.findById(
                        enrollment.getCourseId()
                );

        if(course == null) {
            return "Course not found.";
        }

        for(Enrollment e : enrollmentRepo.findAll()) {

            if(e.getStudentId() == enrollment.getStudentId()
                    &&
                    e.getCourseId() == enrollment.getCourseId()) {

                return "Student already enrolled.";
            }
        }

        enrollmentRepo.save(enrollment);

        return "Enrollment successful.";
    }

    public List<Integer> getCoursesByStudent(int studentId) {

        List<Integer> courseList = new ArrayList<>();

        for(Enrollment e : enrollmentRepo.findAll()) {

            if(e.getStudentId() == studentId) {
                courseList.add(e.getCourseId());
            }
        }

        return courseList;
    }
    
    //View Courses of Student
    public void viewCoursesOfStudent(int studentId) {

        boolean found = false;

        for(Enrollment enrollment :
                enrollmentRepo.findAll()) {

            if(enrollment.getStudentId() == studentId) {

                Course course =
                        courseRepo.findById(
                                enrollment.getCourseId()
                        );

                if(course != null) {

                    System.out.println(course);

                    found = true;
                }
            }
        }

        if(!found) {

            System.out.println(
                    "No courses found for this student."
            );
        }
    }
    
    //View Students in Course
    public void viewStudentsInCourse(int courseId) {

        boolean found = false;

        for(Enrollment enrollment :
                enrollmentRepo.findAll()) {

            if(enrollment.getCourseId() == courseId) {

                Student student =
                        studentRepo.findById(
                                enrollment.getStudentId()
                        );

                if(student != null) {

                    System.out.println(student);

                    found = true;
                }
            }
        }

        if(!found) {

            System.out.println(
                    "No students enrolled in this course."
            );
        }
    }
}