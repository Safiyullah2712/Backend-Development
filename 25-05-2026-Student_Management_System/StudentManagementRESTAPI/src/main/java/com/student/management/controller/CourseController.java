package com.student.management.controller;

import com.student.management.model.Course;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.student.management.service.CourseService;

import java.util.Collection;

@RestController

@RequestMapping("/courses")

public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {

        this.service = service;
    }

    // CREATE COURSE
    @PostMapping

    public ResponseEntity<String> addCourse(
            @RequestBody Course course) {

        String response =
                service.addCourse(course);

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }

    // GET ALL COURSES
    @GetMapping

    public ResponseEntity<Collection<Course>>
    getAllCourses() {

        return new ResponseEntity<>(
                service.getAllCourses(),
                HttpStatus.OK
        );
    }

    // UPDATE COURSE
    @PutMapping("/{courseId}")

    public ResponseEntity<String> updateCourse(

            @PathVariable int courseId,

            @RequestBody Course course) {

        String response =
                service.updateCourse(
                        courseId,
                        course.getCourseName(),
                        course.getInstructorName()
                );

        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }

    // DELETE COURSE
    @DeleteMapping("/{courseId}")

    public ResponseEntity<String> deleteCourse(

            @PathVariable int courseId) {

        String response =
                service.deleteCourse(courseId);

        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }
}