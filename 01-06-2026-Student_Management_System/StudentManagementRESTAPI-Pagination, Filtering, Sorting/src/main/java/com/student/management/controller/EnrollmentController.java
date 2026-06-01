package com.student.management.controller;

import com.student.management.dto.EnrollmentDTO;
import com.student.management.model.Enrollment;
import com.student.management.response.ApiResponse;
import com.student.management.service.EnrollmentService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Enrollment>> addEnrollment(
            @Valid @RequestBody EnrollmentDTO dto) {

        Enrollment enrollment =
                enrollmentService.addEnrollment(dto);

        ApiResponse<Enrollment> response =
                new ApiResponse<>(
                        "Enrollment Added Successfully",
                        enrollment
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Enrollment>>>
    getAllEnrollments() {

        List<Enrollment> enrollments =
                enrollmentService.getAllEnrollments();

        ApiResponse<List<Enrollment>> response =
                new ApiResponse<>(
                        "All Enrollments",
                        enrollments
                );

        return ResponseEntity.ok(response);
    }
}