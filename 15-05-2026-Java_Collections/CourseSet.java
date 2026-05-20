import java.util.HashSet;

public class CourseSet {

    public static void main(String[] args) {

        HashSet<String> courses = new HashSet<>();

        courses.add("CS101");
        courses.add("AI205");
        courses.add("DS301");
        courses.add("AI205");
        courses.add("ML450");

        System.out.println("Course List:"+courses);
    }
}
