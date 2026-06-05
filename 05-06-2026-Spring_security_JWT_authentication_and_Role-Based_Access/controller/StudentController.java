package com.student.management.controller;

import com.student.management.model.Student;
import com.student.management.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // ADMIN can add students
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addStudent(
            @Valid @RequestBody Student student) {

        return new ResponseEntity<>(
                studentService.addStudent(student),
                HttpStatus.CREATED);
    }

    // USER and ADMIN can view all students
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<Student>> getAllStudents() {

        return ResponseEntity.ok(
                studentService.getAllStudents());
    }

    // USER and ADMIN can view student by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Student> getStudentById(
            @PathVariable int id) {

        return ResponseEntity.ok(
                studentService.getStudentById(id));
    }

    // ADMIN can update students
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateStudent(
            @PathVariable int id,
            @Valid @RequestBody Student student) {

        return ResponseEntity.ok(
                studentService.updateStudent(id, student));
    }

    // ADMIN can delete students
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteStudent(
            @PathVariable int id) {

        return ResponseEntity.ok(
                studentService.deleteStudent(id));
    }
}