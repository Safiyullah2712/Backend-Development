package com.student.management.model;

public class Student {

    private Integer id;
    private String name;
    private double marks;

    public Student() {
    }

    public Student(Integer id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }
}