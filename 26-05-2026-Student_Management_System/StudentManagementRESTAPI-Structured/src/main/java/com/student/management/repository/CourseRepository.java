package com.student.management.repository;

import com.student.management.model.Course;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CourseRepository {

    private final Map<Integer, Course> courseMap =
            new HashMap<>();

    public void save(Course course) {

        courseMap.put(
                course.getCourseId(),
                course
        );
    }

    public Course findById(int courseId) {

        return courseMap.get(courseId);
    }

    public Collection<Course> findAll() {

        return courseMap.values();
    }

    public boolean deleteCourse(int courseId) {

        if(courseMap.containsKey(courseId)) {

            courseMap.remove(courseId);

            return true;
        }

        return false;
    }
}