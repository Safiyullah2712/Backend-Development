package com.student.management.service;

import com.student.management.dto.StudentDTO;
import com.student.management.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student addStudent(StudentDTO dto);

    List<Student> getAllStudents();

    Optional<Student> getStudentById(Integer id);

    void deleteStudent(Integer id);

    List<Student> getStudentsByMarks(double marks);
}