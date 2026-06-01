package com.student.management.model;

public class Course {

    private Integer courseId;
    private String courseName;
    private String instructorName;

    public Course() {
    }

    public Course(Integer courseId,
                  String courseName,
                  String instructorName) {

        this.courseId = courseId;
        this.courseName = courseName;
        this.instructorName = instructorName;
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