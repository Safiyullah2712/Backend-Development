package service;

import model.Student;
import repository.StudentRepository;

import java.util.List;

public class StudentService {

    private StudentRepository repository =
            new StudentRepository();

    public String addStudent(Student student) {

        if(repository.findById(student.getId()) != null) {
            return "Duplicate ID not allowed.";
        }

        repository.save(student);

        return "Student added successfully.";
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student getStudentById(int id) {
        return repository.findById(id);
    }

    public String deleteStudent(int id) {

        boolean deleted = repository.deleteById(id);

        if(deleted) {
            return "Student deleted successfully.";
        }

        return "Student not found.";
    }
}