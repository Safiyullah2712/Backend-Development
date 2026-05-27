package com.student.management.service.impl;

import com.student.management.dto.StudentDTO;
import com.student.management.model.Student;
import com.student.management.mapper.StudentMapper;
import com.student.management.repository.StudentRepository;
import com.student.management.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
    private StudentRepository repository;

    @Autowired
    private StudentMapper mapper;

    @Override
    public Student addStudent(StudentDTO dto) {

        Student student = mapper.toEntity(dto);

        return repository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {

        return repository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(Integer id) {

        return repository.findById(id);
    }

    @Override
    public void deleteStudent(Integer id) {

        repository.deleteById(id);
    }

    @Override
    public List<Student> getStudentsByMarks(double marks) {

        return repository.findAll()
                .stream()
                .filter(student -> student.getMarks() >= marks)
                .toList();
    }
}