package com.student.management.repository;

import com.student.management.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {

    private final List<Course> courses = new ArrayList<>();

    public Course save(Course course) {

        courses.add(course);

        return course;
    }

    public List<Course> findAll() {

        return courses;
    }

    public Optional<Course> findById(Integer id) {

        return courses.stream()
                .filter(course ->
                        course.getCourseId().equals(id))
                .findFirst();
    }

    public void deleteById(Integer id) {

        courses.removeIf(course ->
                course.getCourseId().equals(id));
    }
}