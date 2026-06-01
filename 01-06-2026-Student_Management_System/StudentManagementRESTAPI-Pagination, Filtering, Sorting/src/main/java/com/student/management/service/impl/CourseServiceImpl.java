package com.student.management.service.impl;

import com.student.management.dto.CourseDTO;
import com.student.management.model.Course;
import com.student.management.mapper.CourseMapper;
import com.student.management.repository.CourseRepository;
import com.student.management.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository repository;

    @Autowired
    private CourseMapper mapper;

    @Override
    public Course addCourse(CourseDTO dto) {

        Course course = mapper.toEntity(dto);

        return repository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {

        return repository.findAll();
    }

    @Override
    public Optional<Course> getCourseById(Integer id) {

        return repository.findById(id);
    }

    @Override
    public void deleteCourse(Integer id) {

        repository.deleteById(id);
    }

    @Override
    public List<Course> getCoursesByInstructor(String instructorName) {

        return repository.findAll()
                .stream()
                .filter(course ->
                        course.getInstructorName()
                                .equalsIgnoreCase(instructorName))
                .toList();
    }
}