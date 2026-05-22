package controller;

import model.Student;
import service.StudentService;

import java.util.Scanner;
import java.util.logging.Logger;

public class StudentController {

    private static final Logger logger =
            Logger.getLogger(StudentController.class.getName());

    private StudentService service =
            new StudentService();

    private Scanner sc = new Scanner(System.in);

    public void start() {

        int choice;

        do {

            logger.info("===== Student Menu =====");

            logger.info("1. Add Student");
            logger.info("2. View Students");
            logger.info("3. Search Student");
            logger.info("4. Delete Student");
            logger.info("5. Exit");

            choice = sc.nextInt();
            
            switch(choice) {
            case 1:
                addStudent();
                break;

            case 2:
            viewStudents();
            break;

        case 3:
            searchStudent();
            break;

        case 4:
            deleteStudent();
            break;

        case 5:
            logger.info("Returning...");
            break;

        default:
            logger.warning("Invalid Choice");
    }

} while(choice != 5);
}

private void addStudent() {

logger.info("Enter ID:");
int id = sc.nextInt();

sc.nextLine();

logger.info("Enter Name:");
String name = sc.nextLine();

logger.info("Enter Marks:");
double marks = sc.nextDouble();

Student student =
        new Student(id, name, marks);

logger.info(service.addStudent(student));
}

private void viewStudents() {

for(Student s : service.getAllStudents()) {
    logger.info(s.toString());
}
}

private void searchStudent() {

logger.info("Enter Student ID:");

int id = sc.nextInt();

Student student = service.getStudentById(id);

if(student != null) {
    logger.info(student.toString());
}
else {
    logger.warning("Student not found.");
}
}

private void deleteStudent() {

logger.info("Enter Student ID:");

int id = sc.nextInt();

logger.info(service.deleteStudent(id));
}
}
