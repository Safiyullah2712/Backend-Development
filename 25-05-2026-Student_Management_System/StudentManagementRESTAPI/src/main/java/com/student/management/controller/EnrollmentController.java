package com.student.management.controller;

import com.student.management.model.Enrollment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.student.management.service.EnrollmentService;

@RestController

@RequestMapping("/enrollments")

public class EnrollmentController {

    private final EnrollmentService service;

    public EnrollmentController(
            EnrollmentService service) {

        this.service = service;
    }

    // ENROLL STUDENT
    @PostMapping

    public ResponseEntity<String> enrollStudent(

            @RequestBody Enrollment enrollment) {

        String response =
                service.enrollStudent(enrollment);

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }

    // UNENROLL STUDENT
    @DeleteMapping

    public ResponseEntity<String> unenrollStudent(

            @RequestParam int studentId,

            @RequestParam int courseId) {

        String response =
                service.unenrollStudent(
                        studentId,
                        courseId
                );

        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }
}