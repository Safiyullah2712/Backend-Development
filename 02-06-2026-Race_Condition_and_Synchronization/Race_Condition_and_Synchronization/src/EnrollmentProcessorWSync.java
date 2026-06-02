import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EnrollmentProcessorWSync {

    private static int totalEnrollmentsProcessed = 0;

    private static synchronized void incrementCount() {
        totalEnrollmentsProcessed++;
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int task = 1; task <= 50; task++) {

            int taskId = task;

            executorService.submit(() -> {

                String threadName = Thread.currentThread().getName();

                System.out.println(
                        "Task " + taskId +
                        " started by " + threadName);

                for (int i = 0; i < 1000; i++) {
                    incrementCount();
                }

                System.out.println(
                        "Task " + taskId +
                        " completed by " + threadName);
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println(
                "\nTotal Enrollments Processed(With Synchronization) = "
                        + totalEnrollmentsProcessed);
    }
}