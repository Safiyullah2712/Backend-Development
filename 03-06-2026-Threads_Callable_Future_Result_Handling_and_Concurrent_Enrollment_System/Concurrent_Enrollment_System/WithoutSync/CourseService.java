package WithoutSync;

import java.util.HashSet;
import java.util.Set;

public class CourseService {

    private final int capacity = 30;

    private Set<Integer> enrolledStudents =
            new HashSet<>();

    public boolean enrollStudent(
            int studentId) {

        if (enrolledStudents.size()
                >= capacity) {

            return false;
        }

        if (enrolledStudents.contains(
                studentId)) {

            return false;
        }

        enrolledStudents.add(studentId);

        return true;
    }

    public int getEnrollmentCount() {

        return enrolledStudents.size();
    }
}
