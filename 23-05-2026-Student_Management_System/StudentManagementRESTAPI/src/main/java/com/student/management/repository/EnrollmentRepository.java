package com.student.management.repository;

import com.student.management.model.Enrollment;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository

public class EnrollmentRepository {

    private final List<Enrollment> enrollments =
            new ArrayList<>();

    public void save(Enrollment enrollment) {

        enrollments.add(enrollment);
    }

    public List<Enrollment> findAll() {

        return enrollments;
    }

    public boolean removeEnrollment(
            int studentId,
            int courseId) {

        Iterator<Enrollment> iterator =
                enrollments.iterator();

        while(iterator.hasNext()) {

            Enrollment enrollment =
                    iterator.next();

            if(enrollment.getStudentId()
                    == studentId

                    &&

                    enrollment.getCourseId()
                    == courseId) {

                iterator.remove();

                return true;
            }
        }

        return false;
    }

    public void removeByCourseId(int courseId) {

        enrollments.removeIf(

                enrollment ->

                        enrollment.getCourseId()
                                == courseId
        );
    }
}