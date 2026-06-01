package com.student.management.service;

import com.student.management.dto.CourseDTO;
import com.student.management.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    Course addCourse(CourseDTO dto);

    List<Course> getAllCourses();

    Optional<Course> getCourseById(Integer id);

    void deleteCourse(Integer id);

    List<Course> getCoursesByInstructor(String instructorName);
}