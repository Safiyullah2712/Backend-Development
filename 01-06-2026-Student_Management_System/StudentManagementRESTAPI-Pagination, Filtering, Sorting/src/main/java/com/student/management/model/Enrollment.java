package com.student.management.model;

public class Enrollment {

    private Integer enrollmentId;
    private Integer studentId;
    private Integer courseId;

    public Enrollment() {
    }

    public Enrollment(Integer enrollmentId,
                      Integer studentId,
                      Integer courseId) {

        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
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