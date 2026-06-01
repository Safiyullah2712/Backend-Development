package com.student.management.multithreading;

public class SMain {

    public static void main(String[] args) {

        EnrollmentProcessor processor =
                new EnrollmentProcessor();

        long startTime =
                System.currentTimeMillis();

        for (int i = 1; i <= 50; i++) {

            processor.processEnrollment(i);
        }

        long endTime =
                System.currentTimeMillis();

        System.out.println(
                "\nSequential Processing Time: "
                        + (endTime - startTime)
                        + " ms");
    }
}