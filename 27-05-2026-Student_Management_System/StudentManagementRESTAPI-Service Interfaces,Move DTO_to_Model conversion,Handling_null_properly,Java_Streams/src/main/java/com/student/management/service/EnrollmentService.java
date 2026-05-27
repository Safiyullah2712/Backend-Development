package com.student.management.service;

import com.student.management.dto.EnrollmentDTO;
import com.student.management.model.Enrollment;

import java.util.List;

public interface EnrollmentService {

    Enrollment addEnrollment(EnrollmentDTO dto);

    List<Enrollment> getAllEnrollments();
}