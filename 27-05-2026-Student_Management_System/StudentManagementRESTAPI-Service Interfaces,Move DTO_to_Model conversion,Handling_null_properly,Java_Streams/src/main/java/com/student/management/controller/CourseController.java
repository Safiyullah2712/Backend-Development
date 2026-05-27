package com.student.management.controller;

import com.student.management.dto.CourseDTO;
import com.student.management.model.Course;
import com.student.management.response.ApiResponse;
import com.student.management.service.CourseService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Course>> addCourse(
            @Valid @RequestBody CourseDTO dto) {

        Course savedCourse = courseService.addCourse(dto);

        ApiResponse<Course> response =
                new ApiResponse<>(
                        "Course Added Successfully",
                        savedCourse
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses() {

        List<Course> courses = courseService.getAllCourses();

        ApiResponse<List<Course>> response =
                new ApiResponse<>(
                        "All Courses",
                        courses
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(
            @PathVariable Integer id) {

        Course course = courseService.getCourseById(id)
                .orElseThrow(() ->
                        new RuntimeException("Course Not Found"));

        ApiResponse<Course> response =
                new ApiResponse<>(
                        "Course Found",
                        course
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/instructor/{name}")
    public ResponseEntity<ApiResponse<List<Course>>>
    getCoursesByInstructor(@PathVariable String name) {

        List<Course> courses =
                courseService.getCoursesByInstructor(name);

        ApiResponse<List<Course>> response =
                new ApiResponse<>(
                        "Courses By Instructor",
                        courses
                );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCourse(
            @PathVariable Integer id) {

        courseService.deleteCourse(id);

        ApiResponse<String> response =
                new ApiResponse<>(
                        "Course Deleted Successfully",
                        null
                );

        return ResponseEntity.ok(response);
    }
}