package com.student.management.controller;
import jakarta.validation.Valid;

import com.student.management.dto.EnrollmentDTO;

import com.student.management.model.Enrollment;
import com.student.management.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService service;

    public EnrollmentController(
            EnrollmentService service) {

        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String>
    enrollStudent(
            @Valid
            @RequestBody EnrollmentDTO dto) {

        Enrollment enrollment =
                new Enrollment(
                        dto.getEnrollmentId(),
                        dto.getStudentId(),
                        dto.getCourseId()
                );

        return new ResponseEntity<>(
                service.enrollStudent(enrollment),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping
    public ResponseEntity<String>
    unenrollStudent(
            @RequestParam int studentId,
            @RequestParam int courseId) {

        return new ResponseEntity<>(
                service.unenrollStudent(
                        studentId,
                        courseId
                ),
                HttpStatus.OK
        );
    }
}