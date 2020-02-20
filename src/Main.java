import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        String file = "src/res/a_example.txt";

        Object[] data = Parser.readFile(file);

        int days = (int) data[0];
        Book[] allBooks = (Book[]) data[1];
        Library[] allLibraries = (Library[]) data[2];
        System.out.println(Arrays.toString(allLibraries));
    }
}
