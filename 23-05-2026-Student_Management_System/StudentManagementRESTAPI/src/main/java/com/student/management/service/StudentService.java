package com.student.management.service;

import com.student.management.model.Student;

import com.student.management.repository.StudentRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class StudentService {

    private final StudentRepository repository;

    public StudentService(
            StudentRepository repository) {

        this.repository = repository;
    }

    // ADD STUDENT
    public String addStudent(Student student) {

        if(repository.findById(
                student.getId()) != null) {

            return "Duplicate student ID.";
        }

        repository.save(student);

        return "Student added successfully.";
    }

    // GET ALL STUDENTS
    public List<Student> getAllStudents() {

        return repository.findAll();
    }
}