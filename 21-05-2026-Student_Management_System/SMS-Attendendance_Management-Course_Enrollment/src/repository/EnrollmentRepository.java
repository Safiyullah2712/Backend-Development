package repository;

import model.Enrollment;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentRepository {

    private static final List<Enrollment> enrollments =
            new ArrayList<>();

    public void save(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public List<Enrollment> findAll() {
        return enrollments;
    }
}