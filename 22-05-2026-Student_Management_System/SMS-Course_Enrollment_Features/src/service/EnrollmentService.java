package service;

import model.Course;
import model.Enrollment;
import model.Student;

import repository.CourseRepository;
import repository.EnrollmentRepository;
import repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

public class EnrollmentService {

    private EnrollmentRepository enrollmentRepo =
            new EnrollmentRepository();

    private StudentRepository studentRepo =
            new StudentRepository();

    private CourseRepository courseRepo =
            new CourseRepository();

    //Enroll Student
    public String enrollStudent(
            Enrollment enrollment) {

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

        //Max 5 Courses Validation
        int count = 0;

        for(Enrollment e :
                enrollmentRepo.findAll()) {

            if(e.getStudentId()
                    == enrollment.getStudentId()) {

                count++;
            }

            //Duplicate Check
            if(e.getStudentId()
                    == enrollment.getStudentId()

                    &&

                    e.getCourseId()
                    == enrollment.getCourseId()) {

                return "Student already enrolled.";
            }
        }

        if(count >= 5) {

            return "Student can enroll only in 5 courses.";
        }

        enrollmentRepo.save(enrollment);

        return "Enrollment successful.";
    }

    //Unenroll Student
    public String unenrollStudent(
            int studentId,
            int courseId) {

        boolean removed =
                enrollmentRepo.removeEnrollment(
                        studentId,
                        courseId
                );

        if(removed) {

            return "Student unenrolled successfully.";
        }

        return "Enrollment not found.";
    }

    //View Courses of Student
    public void viewCoursesOfStudent(
            int studentId) {

        boolean found = false;

        for(Enrollment enrollment :
                enrollmentRepo.findAll()) {

            if(enrollment.getStudentId()
                    == studentId) {

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
                    "No courses found."
            );
        }
    }

    //View Students in Course
    public void viewStudentsInCourse(
            int courseId) {

        boolean found = false;

        for(Enrollment enrollment :
                enrollmentRepo.findAll()) {

            if(enrollment.getCourseId()
                    == courseId) {

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
                    "No students enrolled."
            );
        }
    }

    //Students Enrolled in Multiple Courses
    public void studentsInMultipleCourses() {

        Map<Integer, Integer> countMap =
                new HashMap<>();

        for(Enrollment e :
                enrollmentRepo.findAll()) {

            countMap.put(
                    e.getStudentId(),

                    countMap.getOrDefault(
                            e.getStudentId(),
                            0
                    ) + 1
            );
        }

        for(Integer studentId :
                countMap.keySet()) {

            if(countMap.get(studentId) > 1) {

                Student student =
                        studentRepo.findById(studentId);

                System.out.println(student);
            }
        }
    }

    //Count Students Per Course
    public void countStudentsPerCourse() {

        Map<Integer, Integer> courseCount =
                new HashMap<>();

        for(Enrollment e :
                enrollmentRepo.findAll()) {

            courseCount.put(
                    e.getCourseId(),

                    courseCount.getOrDefault(
                            e.getCourseId(),
                            0
                    ) + 1
            );
        }

        for(Integer courseId :
                courseCount.keySet()) {

            Course course =
                    courseRepo.findById(courseId);

            System.out.println(
                    course.getCourseName()
                            + " -> "
                            + courseCount.get(courseId)
            );
        }
    }

    //Top 3 Popular Courses
    public void top3Courses() {

        Map<Integer, Integer> courseCount =
                new HashMap<>();

        for(Enrollment e :
                enrollmentRepo.findAll()) {

            courseCount.put(
                    e.getCourseId(),

                    courseCount.getOrDefault(
                            e.getCourseId(),
                            0
                    ) + 1
            );
        }

        courseCount.entrySet()

                .stream()

                .sorted(
                        (a, b) ->
                                b.getValue()
                                        - a.getValue()
                )

                .limit(3)

                .forEach(entry -> {

                    Course course =
                            courseRepo.findById(
                                    entry.getKey()
                            );

                    System.out.println(
                            course.getCourseName()
                                    + " -> "
                                    + entry.getValue()
                    );
                });
    }
}