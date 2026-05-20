import java.util.ArrayList;

public class MobileList {
    public static void main(String[] args) {

        ArrayList<String> mobiles = new ArrayList<>();

        mobiles.add("Samsung");
        mobiles.add("Realme");
        mobiles.add("OnePlus");
        mobiles.add("Apple");
        mobiles.add("Vivo");

        System.out.println("Mobile Brands:");
        for(String brand : mobiles) {
            System.out.println(brand);
        }
    }
}
