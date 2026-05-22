package repository;

import model.Course;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CourseRepository {

    private static final Map<Integer, Course> courseMap =
            new HashMap<>();

    //Save Course
    public void save(Course course) {

        courseMap.put(course.getCourseId(), course);
    }

    //Find Course
    public Course findById(int courseId) {

        return courseMap.get(courseId);
    }

    //Get All Courses
    public Collection<Course> findAll() {

        return courseMap.values();
    }

    //Delete Course
    public boolean deleteCourse(int courseId) {

        if(courseMap.containsKey(courseId)) {

            courseMap.remove(courseId);

            return true;
        }

        return false;
    }
}