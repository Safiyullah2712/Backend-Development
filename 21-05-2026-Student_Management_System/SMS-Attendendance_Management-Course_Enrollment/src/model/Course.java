package model;

public class Course {

    private int courseId;
    private String courseName;
    private String instructorName;

    public Course(int courseId,
                  String courseName,
                  String instructorName) {

        this.courseId = courseId;
        this.courseName = courseName;
        this.instructorName = instructorName;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructorName() {
        return instructorName;
    }

    @Override
    public String toString() {

        return "Course ID: " + courseId +
                ", Course Name: " + courseName +
                ", Instructor: " + instructorName;
    }
}