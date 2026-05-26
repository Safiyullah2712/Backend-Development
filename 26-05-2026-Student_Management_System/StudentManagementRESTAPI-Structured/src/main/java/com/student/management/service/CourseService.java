package com.student.management.service;

import com.student.management.exception.ResourceNotFoundException;

import com.student.management.model.Course;

import com.student.management.repository.CourseRepository;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public Course addCourse(Course course) {

        repository.save(course);

        return course;
    }
    
    public Collection<Course> getAllCourses() {

        return repository.findAll();
    }

    public String updateCourse(
            int courseId,
            Course course) {

        Course existing =
                repository.findById(courseId);

        if(existing == null) {

            throw new ResourceNotFoundException(
                    "Course not found"
            );
        }

        existing.setCourseName(
                course.getCourseName()
        );

        existing.setInstructorName(
                course.getInstructorName()
        );

        repository.save(existing);

        return "Course updated successfully";
    }
    
    public String deleteCourse(int courseId) {

        boolean deleted =
                repository.deleteCourse(courseId);

        if(!deleted) {

            throw new ResourceNotFoundException(
                    "Course not found"
            );
        }

        return "Course deleted successfully";
    }
}