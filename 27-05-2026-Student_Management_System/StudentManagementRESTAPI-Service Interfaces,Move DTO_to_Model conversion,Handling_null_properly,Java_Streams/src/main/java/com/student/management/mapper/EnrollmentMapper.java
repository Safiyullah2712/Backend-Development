package com.student.management.mapper;

import com.student.management.dto.EnrollmentDTO;
import com.student.management.model.Enrollment;

import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {

    public Enrollment toEntity(EnrollmentDTO dto) {

        Enrollment enrollment = new Enrollment();

        enrollment.setEnrollmentId(dto.getEnrollmentId());

        return enrollment;
    }
}