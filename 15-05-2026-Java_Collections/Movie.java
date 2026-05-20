import java.util.Vector;

public class Movie {
    public static void main(String[] args) {

        Vector<String> movies = new Vector<>();

        movies.add("Leo");
        movies.add("Master");
        movies.add("Vikram");

        System.out.println("Movie List:");
        for(String name : movies) {
            System.out.println(name);
        }
    }
}
