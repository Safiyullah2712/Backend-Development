import java.util.TreeMap;

public class SubjectMarks {
    public static void main(String[] args) {

        TreeMap<String, Integer> marks = new TreeMap<>();

        marks.put("Physics", 88);
        marks.put("Maths", 95);
        marks.put("Chemistry", 82);

        System.out.println("Subject Marks:");

        for(String sub : marks.keySet()) {
            System.out.println(sub + " = " + marks.get(sub));
        }
    }
}
