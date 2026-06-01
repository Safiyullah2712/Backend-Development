package com.student.management.repository;

import com.student.management.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {

    private final List<Student> students = new ArrayList<>();

    public Student save(Student student) {

        students.add(student);

        return student;
    }

    public List<Student> findAll() {

        return students;
    }

    public Optional<Student> findById(Integer id) {

        return students.stream()
                .filter(student ->
                        student.getId().equals(id))
                .findFirst();
    }

    public void deleteById(Integer id) {

        students.removeIf(student ->
                student.getId().equals(id));
    }
}