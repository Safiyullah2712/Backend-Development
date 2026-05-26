package com.student.management.controller;

import com.student.management.dto.StudentDTO;

import com.student.management.model.Student;

import com.student.management.service.StudentService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<Student>
    addStudent(
            @Valid
            @RequestBody StudentDTO dto) {

        Student student =
                new Student(
                        dto.getId(),
                        dto.getName(),
                        dto.getMarks()
                );

        Student savedStudent =
                service.addStudent(student);

        return new ResponseEntity<>(
                savedStudent,
                HttpStatus.CREATED
        );
    }
    
    @GetMapping
    public ResponseEntity<List<Student>>
    getAllStudents() {

        return new ResponseEntity<>(
                service.getAllStudents(),
                HttpStatus.OK
        );
    }
}