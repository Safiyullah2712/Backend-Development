import java.util.HashSet;

public class TEaFlavours {
    public static void main(String[] args) {

        HashSet<String> teas = new HashSet<>();

        teas.add("Masala Tea");
        teas.add("Green Tea");
        teas.add("Lemon Tea");
        teas.add("Green Tea");

        System.out.println("Available Tea Flavors:");

        for(String item : teas) {
            System.out.println(item);
        }
    }
}
