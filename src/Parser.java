import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Parser {
    public static Object[] readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner sc = new Scanner(file);

        int amountOfBooks = sc.nextInt();
        int amountOfLibraries = sc.nextInt();
        int amountOfDay = sc.nextInt();

        Book[] allBooks = new Book[amountOfBooks];
        Library[] libraries = new Library[amountOfLibraries];

        Object[] returnData = new Object[3];
        returnData[0] = amountOfDay;
        returnData[1] = allBooks;

        for(int i = 0; i < amountOfBooks; i++) {
            allBooks[i] = new Book(sc.nextInt(), i);
        }

        for(int i = 0; i < amountOfLibraries; i++) {
            int currentBooks = sc.nextInt();
            libraries[i] = new Library(sc.nextInt(), sc.nextInt());
            for(int j = 0; j < currentBooks; j++) {
                libraries[i].addBook(allBooks[sc.nextInt()]);
            }
            libraries[i].sort();
        }
        returnData[2] = libraries;
        return returnData;
    }

    public static void writeFile() throws Exception {
        File file = new File("output.txt");
        BufferedWriter output = new BufferedWriter(new FileWriter(file));
        output.write("this is stupid");
        output.newLine();
        output.write("ho");
        output.close();
    }

    public static void main(String[] args) throws Exception {
        writeFile();
    }
}
