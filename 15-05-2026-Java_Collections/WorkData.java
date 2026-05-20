import java.util.Hashtable;

public class WorkData {
    public static void main(String[] args) {

        Hashtable<Integer, String> workers = new Hashtable<>();

        workers.put(201, "Aravind");
        workers.put(202, "Nithin");
        workers.put(203, "Kombaiya");

        System.out.println("Worker Details:");

        for(Integer id : workers.keySet()) {
            System.out.println(id + " : " + workers.get(id));
        }
    }
}
