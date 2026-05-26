package com.student.management.service;

import com.student.management.exception.DuplicateResourceException;

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

    public Student addStudent(Student student) {

        if(repository.findById(
                student.getId()) != null) {

            throw new DuplicateResourceException(
                    "Student ID already exists"
            );
        }

        repository.save(student);

        return student;
    }

    public List<Student> getAllStudents() {

        return repository.findAll();
    }
}