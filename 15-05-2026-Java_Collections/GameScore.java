import java.util.HashMap;

public class GameScore {

    public static void main(String[] args) {

        HashMap<String, Integer> scores = new HashMap<>();

        scores.put("Mathan", 95);
        scores.put("Gopi", 88);
        scores.put("Madhu", 91);

        System.out.println(scores);
        System.out.println("Madhu's Score --"+scores.get("Madhu"));
    }
}
