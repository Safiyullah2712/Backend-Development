package com.student.management.service;

import com.student.management.model.Student;
import com.student.management.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public String addStudent(Student student) {

        if (repository.existsById(student.getId())) {
            return "Student ID already exists";
        }

        repository.save(student);

        return "Student Added Successfully";
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student getStudentById(int id) {
        return repository.findById(id);
    }

    public String deleteStudent(int id) {

        if (!repository.existsById(id)) {
            return "Student Not Found";
        }

        repository.deleteById(id);

        return "Student Deleted Successfully";
    }
}