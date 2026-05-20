import java.util.Scanner;

class GradeCalculator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter student marks: ");
        int marks = sc.nextInt();

        if (marks >= 90) {
            System.out.println("Grade: A");
        }
        else if (marks >= 75 && marks <90) {
            System.out.println("Grade: B");
        }
        else if (marks >= 50 && marks < 75) {
            System.out.println("Grade: C");
        }
        else {
            System.out.println("Grade: Fail");
        }

        sc.close();
    }
}
