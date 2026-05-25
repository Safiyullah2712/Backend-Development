package com.student.management.service;

import com.student.management.model.Course;

import com.student.management.repository.CourseRepository;
import com.student.management.repository.EnrollmentRepository;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service

public class CourseService {

    private final CourseRepository repository;

    private final EnrollmentRepository enrollmentRepo;

    public CourseService(
            CourseRepository repository,
            EnrollmentRepository enrollmentRepo) {

        this.repository = repository;
        this.enrollmentRepo = enrollmentRepo;
    }

    // ADD COURSE
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

    // GET ALL COURSES
    public Collection<Course> getAllCourses() {

        return repository.findAll();
    }

    // UPDATE COURSE
    public String updateCourse(
            int courseId,
            String courseName,
            String instructorName) {

        Course existing =
                repository.findById(courseId);

        if(existing == null) {

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

    // DELETE COURSE
    public String deleteCourse(int courseId) {

        boolean deleted =
                repository.deleteCourse(courseId);

        if(deleted) {

            enrollmentRepo.removeByCourseId(courseId);

            return "Course deleted successfully.";
        }

        return "Course not found.";
    }
}