package WithoutSync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args)
            throws Exception {

        CourseService service =
                new CourseService();

        ExecutorService executor =
                Executors.newFixedThreadPool(10);

        List<Future<Boolean>> results =
                new ArrayList<>();

        for (int studentId = 1;
             studentId <= 100;
             studentId++) {

            results.add(
                    executor.submit(
                            new Enrollment(
                                    studentId,
                                    service)));
        }

        int successCount = 0;
        int failureCount = 0;

        for (Future<Boolean> future
                : results) {

            if (future.get()) {

                successCount++;

            } else {

                failureCount++;
            }
        }

        executor.shutdown();

        System.out.println(
                "\n===== SUMMARY =====");

        System.out.println(
                "Enrolled : "
                        + successCount);

        System.out.println(
                "Rejected : "
                        + failureCount);

        System.out.println(
                "Final Count : "
                        + service
                        .getEnrollmentCount());
    }
}
