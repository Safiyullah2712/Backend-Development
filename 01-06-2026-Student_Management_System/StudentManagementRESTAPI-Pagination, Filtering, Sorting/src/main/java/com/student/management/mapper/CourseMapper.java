package com.student.management.mapper;

import com.student.management.dto.CourseDTO;
import com.student.management.model.Course;

import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public Course toEntity(CourseDTO dto) {

        Course course = new Course();

        course.setCourseId(dto.getCourseId());
        course.setCourseName(dto.getCourseName());
        course.setInstructorName(dto.getInstructorName());

        return course;
    }
}