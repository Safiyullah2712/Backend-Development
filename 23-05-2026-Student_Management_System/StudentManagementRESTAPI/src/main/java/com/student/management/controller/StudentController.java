package com.student.management.controller;

import com.student.management.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.student.management.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")

public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {

        this.service = service;
    }

    // CREATE STUDENT
    @PostMapping

    public ResponseEntity<String> addStudent(
            @RequestBody Student student) {

        String response =
                service.addStudent(student);

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }

    // GET ALL STUDENTS
    @GetMapping

    public ResponseEntity<List<Student>>
    getAllStudents() {

        return new ResponseEntity<>(
                service.getAllStudents(),
                HttpStatus.OK
        );
    }
}