package repository;

import model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentRepository {

    private static final List<Student> students =
            new ArrayList<>();

    private static final Map<Integer, Student> studentMap =
            new HashMap<>();

    public void save(Student student) {

        students.add(student);
        studentMap.put(student.getId(), student);
    }

    public List<Student> findAll() {
        return students;
    }

    public Student findById(int id) {
        return studentMap.get(id);
    }

    public boolean deleteById(int id) {

        Student student = studentMap.get(id);

        if(student != null) {

            students.remove(student);
            studentMap.remove(id);

            return true;
        }

        return false;
    }
}