class StudentArray {

    public static void main(String[] args) {

        String[] students = {
            "Arun",
            "Priya",
            "Kavin",
            "Mani",
            "Rahul"
        };

        System.out.println("Student List");
        System.out.println("-------------");

        for (String name : students) {
            System.out.println(name);
        }
    }
}
