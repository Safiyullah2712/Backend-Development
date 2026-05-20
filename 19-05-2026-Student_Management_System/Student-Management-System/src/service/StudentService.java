package service;

import model.Student;
import repository.StudentRepository;

import java.util.ArrayList;

public class StudentService {

    private StudentRepository repository = new StudentRepository();

    // Add Student
    public String addStudent(Student student) {

        if(repository.findById(student.getId()) != null) {
            return "Duplicate ID not allowed.";
        }

        repository.save(student);

        return "Student added successfully.";
    }

    // Get All Students
    public ArrayList<Student> getAllStudents() {

        return repository.findAll();
    }

    // Search Student
    public Student getStudentById(int id) {

        return repository.findById(id);
    }

    // Delete Student
    public String deleteStudent(int id) {

        boolean deleted = repository.deleteById(id);

        if(deleted) {
            return "Student deleted successfully.";
        }

        return "Student not found.";
    }

    // Find Topper
    public Student getTopper() {

        ArrayList<Student> students = repository.findAll();

        if(students.isEmpty()) {
            return null;
        }

        Student topper = students.get(0);

        for(Student s : students) {

            if(s.getMarks() > topper.getMarks()) {
                topper = s;
            }
        }

        return topper;
    }
}