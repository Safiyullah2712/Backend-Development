import java.util.Scanner; 
 
public class Main { 
 
    public static void main(String[] args) { 
 
        Scanner sc = new Scanner(System.in); 
 
        StudentService service = new StudentService(); 
 
        int choice; 
 
        do { 
 
            System.out.println("\n===== Student Management Menu 
====="); 
 
            System.out.println("1. Add Student"); 
            System.out.println("2. View Students"); 
            System.out.println("3. Search Student by ID"); 
            System.out.println("4. Delete Student"); 
            System.out.println("5. Find Topper"); 
            System.out.println("6. Exit"); 
 
            System.out.print("Enter choice: "); 
            choice = sc.nextInt(); 
 
            switch(choice) { 
 
                case 1: 
 
                    System.out.print("Enter ID: "); 
                    int id = sc.nextInt(); 
 
                    sc.nextLine(); 
 
                    System.out.print("Enter Name: "); 
                    String name = sc.nextLine(); 
 
                    System.out.print("Enter Marks: "); 
                    double marks = sc.nextDouble(); 
 
                    Student s = new Student(id, name, marks); 
 
                    service.addStudent(s); 
 
                    break; 
 
                case 2: 
 
                    service.getAllStudents(); 
 
                    break; 
 
                case 3: 
 
                    System.out.print("Enter Student ID: "); 
                    int searchId = sc.nextInt(); 
 
                    service.getStudentById(searchId); 
 
                    break; 
 
                case 4: 
 
                    System.out.print("Enter Student ID to delete: "); 
                    int deleteId = sc.nextInt(); 
 
                    service.deleteStudent(deleteId); 
 
                    break; 
 
                case 5: 
 
                    service.getTopper(); 
 
                    break; 
 
                case 6: 
 
                    System.out.println("Application Closed."); 
 
                    break; 
 
                default: 
 
                    System.out.println("Invalid choice."); 
            } 
 
        } while(choice != 6); 
 
        sc.close(); 
    } 
}