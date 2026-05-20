import java.util.ArrayList; 
import java.util.HashMap; 
 
public class StudentService { 
 
    private ArrayList<Student> studentList = new ArrayList<>(); 
    private HashMap<Integer, Student> studentMap = new 
HashMap<>(); 
 
    //Add Student 
    public void addStudent(Student s) { 
 
        if(studentMap.containsKey(s.getId())) { 
            System.out.println("Duplicate ID not allowed."); 
            return; 
        } 
 
        studentList.add(s); 
        studentMap.put(s.getId(), s); 
 
        System.out.println("Student added successfully."); 
    } 
 
    //View All Students 
    public void getAllStudents() { 
 
        if(studentList.isEmpty()) { 
            System.out.println("No student records found."); 
            return; 
        } 
 
        for(Student s : studentList) { 
            System.out.println(s); 
        } 
    } 
 
    //Search Student by ID 
    public void getStudentById(int id) { 
 
        if(studentMap.containsKey(id)) { 
            System.out.println(studentMap.get(id)); 
        } 
        else { 
            System.out.println("Student not found."); 
        } 
    } 
 
    //Delete Student 
    public void deleteStudent(int id) { 
 
        if(studentMap.containsKey(id)) { 
 
            Student s = studentMap.get(id); 
 
            studentList.remove(s); 
            studentMap.remove(id); 
 
            System.out.println("Student deleted successfully."); 
        } 
        else { 
            System.out.println("Student not found."); 
        } 
    } 
 
    //Find Topper 
    public void getTopper() { 
 
        if(studentList.isEmpty()) { 
            System.out.println("No student data available."); 
            return; 
        } 
 
        Student topper = studentList.get(0); 
 
        for(Student s : studentList) { 
 
            if(s.getMarks() > topper.getMarks()) { 
                topper = s; 
            } 
        } 
 
        System.out.println("Topper Details:"); 
        System.out.println(topper); 
    } 
}