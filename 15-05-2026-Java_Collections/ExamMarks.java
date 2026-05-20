import java.util.TreeSet;

public class ExamMarks {
    public static void main(String[] args) {

        TreeSet<Integer> marks = new TreeSet<>();

        marks.add(78);
        marks.add(92);
        marks.add(65);
        marks.add(85);

        System.out.println("Sorted Marks:");

        for(Integer score : marks) {
            System.out.println(score);
        }
    }
}
