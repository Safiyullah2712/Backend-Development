package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args)
            throws Exception {

        ExecutorService executor =
                Executors.newFixedThreadPool(5);

        List<Future<EnrollmentResult>> futures =
                new ArrayList<>();

        for (int studentId = 1;
             studentId <= 50;
             studentId++) {

            Enrollment task =
                    new Enrollment(
                            studentId);

            Future<EnrollmentResult> future =
                    executor.submit(task);

            futures.add(future);
        }

        int successCount = 0;
        int failureCount = 0;

        for (Future<EnrollmentResult> future
                : futures) {

            EnrollmentResult result =
                    future.get();

            System.out.println(result);

            if ("SUCCESS".equals(
                    result.getStatus())) {

                successCount++;
            } else {

                failureCount++;
            }
        }

        executor.shutdown();

        System.out.println("\n========== SUMMARY ==========");

        System.out.println(
                "Success Count : "
                        + successCount);

        System.out.println(
                "Failure Count : "
                        + failureCount);
    }
}