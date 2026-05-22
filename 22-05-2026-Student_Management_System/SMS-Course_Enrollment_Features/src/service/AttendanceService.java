package service;

import model.Attendance;
import repository.AttendanceRepository;

import java.util.ArrayList;
import java.util.List;

public class AttendanceService {

    private AttendanceRepository repository =
            new AttendanceRepository();

    public String markAttendance(Attendance attendance) {

        repository.save(attendance);

        return "Attendance marked successfully.";
    }

    public List<Attendance> getAttendanceByStudent(int studentId) {

        List<Attendance> result =
                new ArrayList<>();

        for(Attendance attendance : repository.findAll()) {

            if(attendance.getStudentId() == studentId) {
                result.add(attendance);
            }
        }

        return result;
    }
}