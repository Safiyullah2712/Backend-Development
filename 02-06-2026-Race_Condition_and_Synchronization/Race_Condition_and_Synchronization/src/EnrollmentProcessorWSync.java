public class EnrollmentProcessorWSync extends Thread{
	static int totalEnrollmentsProcessed = 0;

    private static synchronized void incrementCount() {
        totalEnrollmentsProcessed++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            incrementCount();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        EnrollmentProcessorWSync t1 = new EnrollmentProcessorWSync();
        EnrollmentProcessorWSync t2 = new EnrollmentProcessorWSync();
        EnrollmentProcessorWSync t3 = new EnrollmentProcessorWSync();

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
