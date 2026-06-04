package com.student.management.controller;

import com.student.management.model.Student;
import com.student.management.service.StudentService;
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
    public String addStudent(
            @RequestBody Student student) {

        return service.addStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {

        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(
            @PathVariable int id) {

        return service.getStudentById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(
            @PathVariable int id) {

        return service.deleteStudent(id);
    }
}