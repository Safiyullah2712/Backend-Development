package service;

import model.Attendance;
import repository.AttendanceRepository;

import java.util.ArrayList;

public class AttendanceService {

    private AttendanceRepository repository =
            new AttendanceRepository();

    //Mark Attendance
    public String markAttendance(
            Attendance attendance) {

        String status =
                attendance.getStatus();

        if(!(status.equalsIgnoreCase("Present")
                || status.equalsIgnoreCase("Absent"))) {

            return "Invalid attendance status.";
        }

        repository.save(attendance);

        return "Attendance marked successfully.";
    }

    //Get Attendance by Student
    public ArrayList<Attendance>
    getAttendanceByStudent(int studentId) {

        return repository.findByStudentId(studentId);
    }

    //Calculate Attendance Percentage
    public double
    calculateAttendancePercentage(int studentId) {

        ArrayList<Attendance> records =
                repository.findByStudentId(studentId);

        if(records.isEmpty()) {

            return 0;
        }

        int presentCount = 0;

        for(Attendance a : records) {

            if(a.getStatus()
                    .equalsIgnoreCase("Present")) {

                presentCount++;
            }
        }

        return ((double) presentCount
                / records.size()) * 100;
    }
}