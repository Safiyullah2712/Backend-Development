package WithoutSync;

import java.util.concurrent.Callable;

public class Enrollment
        implements Callable<Boolean> {

    private final int studentId;

    private final CourseService service;

    public Enrollment(
            int studentId,
            CourseService service) {

        this.studentId = studentId;
        this.service = service;
    }

    @Override
    public Boolean call() {

        boolean success =
                service.enrollStudent(
                        studentId);

        System.out.println(
                Thread.currentThread().getName()
                        + " -> Student "
                        + studentId
                        + " : "
                        + (success
                        ? "ENROLLED"
                        : "REJECTED"));

        return success;
    }
}