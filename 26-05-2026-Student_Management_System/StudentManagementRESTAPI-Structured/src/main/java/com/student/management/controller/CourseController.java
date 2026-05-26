package com.student.management.controller;

import com.student.management.dto.CourseDTO;

import com.student.management.model.Course;

import com.student.management.service.CourseService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Course>
    addCourse(
            @Valid
            @RequestBody CourseDTO dto) {

        Course course =
                new Course(
                        dto.getCourseId(),
                        dto.getCourseName(),
                        dto.getInstructorName()
                );

        return new ResponseEntity<>(
                service.addCourse(course),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<Collection<Course>>
    getAllCourses() {

        return new ResponseEntity<>(
                service.getAllCourses(),
                HttpStatus.OK
        );
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<String>
    updateCourse(
            @PathVariable int courseId,
            @Valid
            @RequestBody CourseDTO dto) {

        Course course =
                new Course(
                        dto.getCourseId(),
                        dto.getCourseName(),
                        dto.getInstructorName()
                );

        return new ResponseEntity<>(
                service.updateCourse(
                        courseId,
                        course
                ),
                HttpStatus.OK
        );
    }
    
    @DeleteMapping("/{courseId}")
    public ResponseEntity<String>
    deleteCourse(
            @PathVariable int courseId) {

        return new ResponseEntity<>(
                service.deleteCourse(courseId),
                HttpStatus.OK
        );
    }
}