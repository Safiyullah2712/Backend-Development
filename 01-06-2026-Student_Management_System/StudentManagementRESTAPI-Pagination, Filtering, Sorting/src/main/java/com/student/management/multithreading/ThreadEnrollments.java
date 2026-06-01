package com.student.management.multithreading;

import java.util.List;

public class ThreadEnrollments
        implements Runnable {

    private final List<Integer> students;

    private final EnrollmentProcessor processor;

    public ThreadEnrollments(
            List<Integer> students,
            EnrollmentProcessor processor) {

        this.students = students;
        this.processor = processor;
    }

    @Override
    public void run() {

        for (Integer studentId : students) {

            processor.processEnrollment(studentId);
        }
    }
}