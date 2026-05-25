package com.student.management.service;

import com.student.management.model.Attendance;
import com.student.management.repository.AttendanceRepository;

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