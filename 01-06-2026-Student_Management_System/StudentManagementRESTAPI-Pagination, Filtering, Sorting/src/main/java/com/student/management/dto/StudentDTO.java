package com.student.management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StudentDTO {

    @NotNull(message = "Student ID cannot be null")
    private Integer id;

    @NotBlank(message = "Student name cannot be empty")
    private String name;

    @Min(value = 0,
            message = "Marks cannot be negative")
    private double marks;

    public StudentDTO() {
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