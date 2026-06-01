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
import java.util.Comparator;

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
    
    @Override
    public List<Student> getStudents(
            int page,
            int size,
            String sortBy,
            String direction) {

        List<Student> students = repository.findAll();

        Comparator<Student> comparator;

        switch (sortBy.toLowerCase()) {

            case "name":
                comparator =
                        Comparator.comparing(
                                Student::getName
                        );
                break;

            case "marks":
                comparator =
                        Comparator.comparingDouble(
                                Student::getMarks
                        );
                break;

            default:
                comparator =
                        Comparator.comparing(
                                Student::getId
                        );
        }

        if ("desc".equalsIgnoreCase(direction)) {
            comparator = comparator.reversed();
        }

        students.sort(comparator);

        int start = page * size;
        int end = Math.min(
                start + size,
                students.size());

        if (start >= students.size()) {
            return List.of();
        }

        return students.subList(start, end);
    }

    @Override
    public List<Student> filterStudents(
            String name,
            Double minMarks) {

        return repository.findAll()
                .stream()

                .filter(student ->

                        (name == null ||
                         student.getName()
                                 .equalsIgnoreCase(name))

                        &&

                        (minMarks == null ||
                         student.getMarks() >= minMarks)
                )

                .toList();
    }
}