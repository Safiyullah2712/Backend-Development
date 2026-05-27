package com.student.management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CourseDTO {

    @NotNull(message = "Course ID cannot be null")
    private Integer courseId;

    @NotBlank(message = "Course name cannot be empty")
    private String courseName;

    @Size(min = 3,
            message = "Instructor name minimum 3 characters")
    private String instructorName;

    public CourseDTO() {
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }
}