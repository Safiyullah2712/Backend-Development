package com.student.management.service.impl;

import com.student.management.dto.EnrollmentDTO;
import com.student.management.mapper.EnrollmentMapper;
import com.student.management.model.Enrollment;
import com.student.management.repository.CourseRepository;
import com.student.management.repository.EnrollmentRepository;
import com.student.management.repository.StudentRepository;
import com.student.management.service.EnrollmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentMapper mapper;

    @Override
    public Enrollment addEnrollment(EnrollmentDTO dto) {

        studentRepository.findById(dto.getStudentId())
                .orElseThrow(() ->
                        new RuntimeException("Student Not Found"));

        courseRepository.findById(dto.getCourseId())
                .orElseThrow(() ->
                        new RuntimeException("Course Not Found"));

        Enrollment enrollment = mapper.toEntity(dto);

        return enrollmentRepository.save(enrollment);
    }

    @Override
    public List<Enrollment> getAllEnrollments() {

        return enrollmentRepository.findAll();
    }
}