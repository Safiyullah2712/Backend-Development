import java.util.LinkedHashMap;

public class TrainPlatforms {
    public static void main(String[] args) {

        LinkedHashMap<String, Integer> trains = new LinkedHashMap<>();

        trains.put("Chennai Express", 2);
        trains.put("Kanyakumari Express", 5);
        trains.put("Nellai Express", 1);

        System.out.println("Train Platform Details:");

        for(String name : trains.keySet()) {
            System.out.println(name + " -> Platform " + trains.get(name));
        }
    }
}
