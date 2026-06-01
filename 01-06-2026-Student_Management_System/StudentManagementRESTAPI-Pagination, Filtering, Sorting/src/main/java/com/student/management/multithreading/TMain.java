package com.student.management.multithreading;

import java.util.ArrayList;
import java.util.List;

public class TMain {

    public static void main(String[] args)
            throws InterruptedException {

        EnrollmentProcessor processor =
                new EnrollmentProcessor();

        List<Integer> students =
                new ArrayList<>();

        for (int i = 1; i <= 50; i++) {

            students.add(i);
        }

        long startTime =
                System.currentTimeMillis();

        Thread t1 =
                new Thread(
                        new ThreadEnrollments(
                                students.subList(0, 10),
                                processor));

        Thread t2 =
                new Thread(
                        new ThreadEnrollments(
                                students.subList(10, 20),
                                processor));

        Thread t3 =
                new Thread(
                        new ThreadEnrollments(
                                students.subList(20, 30),
                                processor));

        Thread t4 =
                new Thread(
                        new ThreadEnrollments(
                                students.subList(30, 40),
                                processor));

        Thread t5 =
                new Thread(
                        new ThreadEnrollments(
                                students.subList(40, 50),
                                processor));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();

        long endTime =
                System.currentTimeMillis();

        System.out.println(
                "\nParallel Processing Time: "
                        + (endTime - startTime)
                        + " ms");
    }
}