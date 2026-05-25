package com.student.management.repository;

import com.student.management.model.Attendance;

import java.util.ArrayList;
import java.util.List;

public class AttendanceRepository {

    private static final List<Attendance> attendanceList =
            new ArrayList<>();

    public void save(Attendance attendance) {
        attendanceList.add(attendance);
    }

    public List<Attendance> findAll() {
        return attendanceList;
    }
}