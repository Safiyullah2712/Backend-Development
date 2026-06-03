package multithreading;

public class EnrollmentResult {

    private int studentId;

    private String status;

    public EnrollmentResult(
            int studentId,
            String status) {

        this.studentId = studentId;
        this.status = status;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {

        return "Student ID: "
                + studentId
                + ", Status: "
                + status;
    }
}