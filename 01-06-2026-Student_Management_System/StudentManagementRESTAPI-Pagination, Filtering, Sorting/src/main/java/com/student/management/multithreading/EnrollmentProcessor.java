package com.student.management.multithreading;

public class EnrollmentProcessor {

    public void processEnrollment(int studentId) {

        try {
            Thread.sleep(100);

            System.out.println(
                    "Student "
                            + studentId
                            + " enrolled by "
                            + Thread.currentThread().getName());

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }
}