package model;

public class Attendance {

    private int attendanceId;
    private int studentId;
    private String date;
    private String status;

    //Constructor
    public Attendance(int attendanceId, int studentId,
                      String date, String status) {

        this.attendanceId = attendanceId;
        this.studentId = studentId;
        this.date = date;
        this.status = status;
    }

    //Getters
    public int getAttendanceId() {
        return attendanceId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    //Setters
    public void setDate(String date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //toString()
    @Override
    public String toString() {

        return "Attendance ID: " + attendanceId +
               ", Student ID: " + studentId +
               ", Date: " + date +
               ", Status: " + status;
    }
}