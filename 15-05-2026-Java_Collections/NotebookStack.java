import java.util.Stack;

public class NotebookStack {
    public static void main(String[] args) {

        Stack<String> colors = new Stack<>();

        colors.push("Blue");
        colors.push("Black");
        colors.push("Green");

        System.out.println("Notebook Colors:");
        for(String item : colors) {
            System.out.println(item);
        }
    }
}
