package repository;

import model.Course;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CourseRepository {

    private static final Map<Integer, Course> courseMap =
            new HashMap<>();

    public void save(Course course) {
        courseMap.put(course.getCourseId(), course);
    }

    public Course findById(int id) {
        return courseMap.get(id);
    }

    public Collection<Course> findAll() {
        return courseMap.values();
    }
}