package com.student.management.mapper;

import com.student.management.dto.StudentDTO;
import com.student.management.model.Student;

import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toEntity(StudentDTO dto) {

        Student student = new Student();

        student.setId(dto.getId());
        student.setName(dto.getName());
        student.setMarks(dto.getMarks());

        return student;
    }
}