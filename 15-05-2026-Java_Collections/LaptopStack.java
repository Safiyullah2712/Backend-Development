import java.util.HashMap;

public class LaptopStack {
    public static void main(String[] args) {

        HashMap<String, Integer> laptops = new HashMap<>();

        laptops.put("HP", 12);
        laptops.put("Dell", 8);
        laptops.put("Lenovo", 15);

        System.out.println("Laptop Stock Details:");

        for(String brand : laptops.keySet()) {
            System.out.println(brand + " : " + laptops.get(brand) + " pieces");
        }
    }
}
