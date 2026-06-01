package com.student.management.repository;

import com.student.management.model.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EnrollmentRepository {

    private final List<Enrollment> enrollments =
            new ArrayList<>();

    public Enrollment save(Enrollment enrollment) {

        enrollments.add(enrollment);

        return enrollment;
    }

    public List<Enrollment> findAll() {

        return enrollments;
    }
}