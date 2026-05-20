import java.util.LinkedList;

public class BusRotes {
    public static void main(String[] args) {

        LinkedList<Integer> routes = new LinkedList<>();

        routes.add(564);
        routes.add(565);
        routes.add(13);

        System.out.println("Bus Route Numbers:");
        for(Integer num : routes) {
            System.out.println(num);
        }
    }
}
