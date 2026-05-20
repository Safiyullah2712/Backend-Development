package repository;

import model.Attendance;

import java.util.ArrayList;
import java.util.HashMap;

public class AttendanceRepository {

    private ArrayList<Attendance> attendanceList =
            new ArrayList<>();

    private HashMap<Integer, Attendance> attendanceMap =
            new HashMap<>();

    //Save Attendance
    public void save(Attendance attendance) {

        attendanceList.add(attendance);

        attendanceMap.put(
                attendance.getAttendanceId(),
                attendance
        );
    }

    // Get All Attendance
    public ArrayList<Attendance> findAll() {

        return attendanceList;
    }

    //Find Attendance by Student ID
    public ArrayList<Attendance> findByStudentId(int studentId) {

        ArrayList<Attendance> result =
                new ArrayList<>();

        for(Attendance a : attendanceList) {

            if(a.getStudentId() == studentId) {

                result.add(a);
            }
        }

        return result;
    }
}