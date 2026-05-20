import java.util.LinkedHashSet;

public class FestivalList {
    public static void main(String[] args) {

        LinkedHashSet<String> festivals = new LinkedHashSet<>();

        festivals.add("Pongal");
        festivals.add("Diwali");
        festivals.add("Christmas");
        festivals.add("Ramzan");

        System.out.println("Festival Names:");

        for(String name : festivals) {
            System.out.println(name);
        }
    }
}
