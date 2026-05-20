package repository;

import model.Student;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentRepository {

    private ArrayList<Student> studentList = new ArrayList<>();
    private HashMap<Integer, Student> studentMap = new HashMap<>();

    // Save Student
    public void save(Student student) {

        studentList.add(student);
        studentMap.put(student.getId(), student);
    }

    // Find All Students
    public ArrayList<Student> findAll() {

        return studentList;
    }

    // Find Student by ID
    public Student findById(int id) {

        return studentMap.get(id);
    }

    // Delete Student
    public boolean deleteById(int id) {

        Student student = studentMap.get(id);

        if(student != null) {

            studentList.remove(student);
            studentMap.remove(id);

            return true;
        }

        return false;
    }
}