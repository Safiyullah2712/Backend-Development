package com.student.management.repository;

import com.student.management.model.Student;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepository {

    private final Map<Integer, Student> studentMap =
            new HashMap<>();

    public void save(Student student) {

        studentMap.put(student.getId(), student);
    }

    public Student findById(int id) {

        return studentMap.get(id);
    }

    public List<Student> findAll() {

        return new ArrayList<>(
                studentMap.values()
        );
    }
}