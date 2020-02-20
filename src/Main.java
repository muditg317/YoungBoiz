public class Main {

    public static void main(String[] args) throws Exception {
        String file = "e_so_many_books.txt";

        Object[] data = Parser.readFile(file);

        int days = (int) data[0];
        Book[] allBooks = (Book[]) data[1];
        Library[] allLibraries = (Library[]) data[2];

    }
}
