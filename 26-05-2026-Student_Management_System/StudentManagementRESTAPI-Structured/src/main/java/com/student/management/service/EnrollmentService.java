package com.student.management.service;

import com.student.management.exception.ResourceNotFoundException;

import com.student.management.model.Course;
import com.student.management.model.Enrollment;
import com.student.management.model.Student;

import com.student.management.repository.CourseRepository;
import com.student.management.repository.EnrollmentRepository;
import com.student.management.repository.StudentRepository;

import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepo;

    private final StudentRepository studentRepo;

    private final CourseRepository courseRepo;

    public EnrollmentService(
            EnrollmentRepository enrollmentRepo,
            StudentRepository studentRepo,
            CourseRepository courseRepo) {

        this.enrollmentRepo = enrollmentRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }
    
    public String enrollStudent(
            Enrollment enrollment) {

        Student student =
                studentRepo.findById(
                        enrollment.getStudentId()
                );

        if(student == null) {

            throw new ResourceNotFoundException(
                    "Student not found"
            );
        }

        Course course =
                courseRepo.findById(
                        enrollment.getCourseId()
                );

        if(course == null) {

            throw new ResourceNotFoundException(
                    "Course not found"
            );
        }

        enrollmentRepo.save(enrollment);

        return "Enrollment successful";
    }

    public String unenrollStudent(
            int studentId,
            int courseId) {

        boolean removed =
                enrollmentRepo.removeEnrollment(
                        studentId,
                        courseId
                );

        if(!removed) {

            throw new ResourceNotFoundException(
                    "Enrollment not found"
            );
        }

        return "Student unenrolled successfully";
    }
}
