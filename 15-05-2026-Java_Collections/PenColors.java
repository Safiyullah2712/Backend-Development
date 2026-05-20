import java.util.ArrayList;

public class PenColors {
    public static void main(String[] args) {

        ArrayList<String> colors = new ArrayList<>();

        colors.add("Blue");
        colors.add("Black");
        colors.add("Red");
        colors.add("Green");

        System.out.println("Pen Colors:");

        for(String color : colors) {
            System.out.println(color);
        }
    }
}
