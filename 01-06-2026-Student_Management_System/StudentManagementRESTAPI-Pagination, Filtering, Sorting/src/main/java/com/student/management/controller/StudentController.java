package com.student.management.controller;

import com.student.management.dto.StudentDTO;
import com.student.management.model.Student;
import com.student.management.response.ApiResponse;
import com.student.management.service.StudentService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Student>> addStudent(
            @Valid @RequestBody StudentDTO dto) {

        Student savedStudent = studentService.addStudent(dto);

        ApiResponse<Student> response =
                new ApiResponse<>(
                        "Student Added Successfully",
                        savedStudent
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents() {

        List<Student> students = studentService.getAllStudents();

        ApiResponse<List<Student>> response =
                new ApiResponse<>(
                        "All Students",
                        students
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(
            @PathVariable Integer id) {

        Student student = studentService.getStudentById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student Not Found"));

        ApiResponse<Student> response =
                new ApiResponse<>(
                        "Student Found",
                        student
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/marks/{marks}")
    public ResponseEntity<ApiResponse<List<Student>>>
    getStudentsByMarks(@PathVariable double marks) {

        List<Student> students =
                studentService.getStudentsByMarks(marks);

        ApiResponse<List<Student>> response =
                new ApiResponse<>(
                        "Students Filtered By Marks",
                        students
                );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteStudent(
            @PathVariable Integer id) {

        studentService.deleteStudent(id);

        ApiResponse<String> response =
                new ApiResponse<>(
                        "Student Deleted Successfully",
                        null
                );

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/paged")
    public ResponseEntity<ApiResponse<List<Student>>>
    getStudents(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String direction) {

        List<Student> students =
                studentService.getStudents(
                        page,
                        size,
                        sortBy,
                        direction
                );

        ApiResponse<List<Student>> response =
                new ApiResponse<>(
                        "Students Retrieved Successfully",
                        students
                );

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/filter")
    public ResponseEntity<ApiResponse<List<Student>>>
    filterStudents(

            @RequestParam(required = false)
            String name,

            @RequestParam(required = false)
            Double minMarks) {

        List<Student> students =
                studentService.filterStudents(
                        name,
                        minMarks
                );

        ApiResponse<List<Student>> response =
                new ApiResponse<>(
                        "Filtered Students",
                        students
                );

        return ResponseEntity.ok(response);
    }
}