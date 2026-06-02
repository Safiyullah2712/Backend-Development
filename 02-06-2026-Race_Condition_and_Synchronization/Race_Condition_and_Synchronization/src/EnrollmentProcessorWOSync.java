public class EnrollmentProcessorWOSync extends Thread {

    static int totalEnrollmentsProcessed = 0;

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            totalEnrollmentsProcessed++;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        EnrollmentProcessorWOSync t1 = new EnrollmentProcessorWOSync();
        EnrollmentProcessorWOSync t2 = new EnrollmentProcessorWOSync();
        EnrollmentProcessorWOSync t3 = new EnrollmentProcessorWOSync();

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Total Enrollments Processed: "
                + totalEnrollmentsProcessed);
    }
}