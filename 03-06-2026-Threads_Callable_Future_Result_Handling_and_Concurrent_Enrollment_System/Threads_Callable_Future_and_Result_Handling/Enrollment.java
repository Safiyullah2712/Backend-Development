package multithreading;

import java.util.Random;
import java.util.concurrent.Callable;

public class Enrollment
        implements Callable<EnrollmentResult> {

    private final int studentId;

    public Enrollment(int studentId) {

        this.studentId = studentId;
    }

    @Override
    public EnrollmentResult call()
            throws Exception {

        System.out.println(
                Thread.currentThread().getName()
                        + " processing Student "
                        + studentId);

        Thread.sleep(100);

        Random random = new Random();

        boolean success =
                random.nextBoolean();

        if (success) {

            return new EnrollmentResult(
                    studentId,
                    "SUCCESS");
        }

        return new EnrollmentResult(
                studentId,
                "FAILURE");
    }
}