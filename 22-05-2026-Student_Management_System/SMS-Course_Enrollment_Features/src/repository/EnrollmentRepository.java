package repository;

import model.Enrollment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EnrollmentRepository {

    private static final List<Enrollment> enrollments =
            new ArrayList<>();

    //Save Enrollment
    public void save(Enrollment enrollment) {

        enrollments.add(enrollment);
    }

    //Get All
    public List<Enrollment> findAll() {

        return enrollments;
    }

    //Remove Enrollment
    public boolean removeEnrollment(
            int studentId,
            int courseId) {

        Iterator<Enrollment> iterator =
                enrollments.iterator();

        while(iterator.hasNext()) {

            Enrollment enrollment =
                    iterator.next();

            if(enrollment.getStudentId() == studentId
                    &&
                    enrollment.getCourseId() == courseId) {

                iterator.remove();

                return true;
            }
        }

        return false;
    }

    //Remove Enrollments by Course
    public void removeByCourseId(int courseId) {

        enrollments.removeIf(
                enrollment ->
                        enrollment.getCourseId()
                                == courseId
        );
    }
}