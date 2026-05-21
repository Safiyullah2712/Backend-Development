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
}