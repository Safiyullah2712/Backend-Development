package com.student.management.dto;

import jakarta.validation.constraints.NotNull;

public class EnrollmentDTO {

    @NotNull(message = "Enrollment ID required")
    private Integer enrollmentId;

    @NotNull(message = "Student ID required")
    private Integer studentId;

    @NotNull(message = "Course ID required")
    private Integer courseId;

    public EnrollmentDTO() {
    }

    public Integer getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Integer enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}