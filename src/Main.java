import java.util.*;

public class Main {
    static int totalDays;
    static Book[] allBooks;
    static ArrayList<Library> allLibraries;
    static Set<Integer> scannedBooks;
    static ArrayList<Library> librarySchedule;
    public static void main(String[] args) throws Exception {
        String file = "e_so_many_books.txt";

        Object[] data = Parser.readFile(file);

        totalDays = (int) data[0];
        allBooks = (Book[]) data[1];
        Library[] arrLibs = (Library[]) data[2];
        allLibraries = new ArrayList<>(Arrays.asList(arrLibs));
        Set<Integer> scannedBooks = new HashSet();
        librarySchedule = maxScoreOverSetUp(totalDays);
    }

    public static ArrayList<Library> maxScoreOverSetUp (int daysLeft) {
        ArrayList<Library> schedule = new ArrayList<>();
        //maxVal is score/setuptime
        int maxVal = allLibraries.get(0).score(daysLeft, scannedBooks) / allLibraries.get(0).signUpTime;
        Library maxLib = allLibraries.get(0);
        for (Library lib : allLibraries) {
            int libVal = lib.score(daysLeft, scannedBooks) / lib.signUpTime;
            if (libVal > maxVal) {
                maxLib = lib;
                maxVal = libVal;
            }
        }
        //we choose maxLib to add to our schedule
        schedule.add(maxLib);
        allLibraries.remove(maxLib);
        return null;



    }
}
