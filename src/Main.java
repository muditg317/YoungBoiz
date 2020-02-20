import java.util.*;

public class Main {
    static int totalDays;
    static Book[] allBooks;
    static ArrayList<Library> allLibraries;
    static Set<Book> scannedBooks;
    static List<Library> librarySchedule;
    public static void main(String[] args) throws Exception {
        //atom://teletype/portal/7d3de1d5-1bed-45cd-9b82-051a56f6d236 <- paste to teletype
        String file = "src/res/b_read_on.txt";

        Object[] data = Parser.readFile(file);

        totalDays = (int) data[0];
        allBooks = (Book[]) data[1];
        Library[] arrLibs = (Library[]) data[2];
        allLibraries = new ArrayList<>(Arrays.asList(arrLibs));
        scannedBooks = new HashSet();
        librarySchedule = generateSchedule(totalDays, new ArrayList<Library>());
        System.out.println(getScoreFromLibraries(librarySchedule));
        Parser.writeFile(librarySchedule);
    }

    public static List<Library> generateSchedule(int daysLeft, List<Library> schedule) {
        //maxVal is score/signUpTime
        if (daysLeft <= 0 || allLibraries.size() == 0) {
            return schedule;
        }

        Library maxLib = maxScoreOverSetUp(daysLeft, scannedBooks);

        //we choose maxLib to add to our schedule
        maxLib.publish(daysLeft, scannedBooks);
        if (maxLib.books.size() > 0) {
            schedule.add(maxLib);
        }
        allLibraries.remove(maxLib);
        daysLeft -= maxLib.signUpTime;
        if (daysLeft <= 0) {
            return schedule; //rippo my bippo
        }
        return generateSchedule(daysLeft - maxLib.signUpTime, schedule);
    }

    public static Library maxScoreOverSetUp(int daysLeft, Set<Book> scannedBooks) {
        int maxVal = allLibraries.get(0).score(daysLeft, scannedBooks) / allLibraries.get(0).signUpTime;
        Library maxLib = allLibraries.get(0);
        for (int i = 1; i < allLibraries.size(); i++) {
            Library lib = allLibraries.get(i);
            if (lib.signUpTime > daysLeft) {
                continue; //ignore this library
            }
            int libVal = lib.score(daysLeft, scannedBooks) / lib.signUpTime;
            if (libVal > maxVal) {
                maxLib = lib;
                maxVal = libVal;
            }
        }
        return maxLib;
    }

    public static Library opCostAlgo (int daysLeft, List<Library> schedule) {
        int maxVal = allLibraries.get(0).score(daysLeft, scannedBooks) / allLibraries.get(0).signUpTime;
        Library maxLib = allLibraries.get(0);
        for (Library lib : allLibraries) {
            if (lib.signUpTime > daysLeft) {
                continue; //ignore this library
            }
            int libVal = lib.score(daysLeft, scannedBooks) / lib.signUpTime;
            if (libVal > maxVal) {
                maxLib = lib;
                maxVal = libVal;
            }
        }
        return maxLib;
    }

    /**
     * [getMaxOpCost description]
     * @param  lib [description]
     * @return     [description]
     */
    private static int getOpCost(Library lib) {
        for (Library curr : allLibraries) {

        }
        return 0;
    }

    private static int getScoreFromLibraries(List<Library> order) {
        int score = 0;
        for (Library library: order) {
            for (Book book: library.books) {
                score += book.getScore();
            }
        }
        return score;
    }

}
