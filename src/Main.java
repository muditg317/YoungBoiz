import java.util.*;

public class Main {
    static int totalDays;
    static Book[] allBooks;
    static ArrayList<Library> allLibraries;
    static Set<Book> scannedBooks;
    static List<Library> librarySchedule;
    public static void main(String[] args) throws Exception {
        //atom://teletype/portal/7d3de1d5-1bed-45cd-9b82-051a56f6d236 <- paste to teletype
        String[] files = {
//            "src/res/a_example.txt",
//            "src/res/b_read_on.txt",
//             "src/res/c_incunabula.txt",
             "src/res/d_tough_choices.txt",
//            "src/res/f_libraries_of_the_world.txt",
//            "src/res/e_so_many_books.txt",
        };

        for (String file : files) {
            System.out.println(file);
            Object[] data = Parser.readFile(file);

            totalDays = (int) data[0];
            allBooks = (Book[]) data[1];
            Library[] arrLibs = (Library[]) data[2];
            allLibraries = new ArrayList<>(Arrays.asList(arrLibs));
            randomlySelectLibraries(1000);
            scannedBooks = new HashSet();
            //librarySchedule = generateSchedule(totalDays, new ArrayList<Library>());
            librarySchedule = generateScheduleIteratively();
            System.out.println(getScoreFromLibraries(librarySchedule));
            Parser.writeFile(file, librarySchedule);
        }
    }
    public static void randomlySelectLibraries(int numLibs) {
        ArrayList<Library> newLibs = new ArrayList<>(numLibs);
        for (int i = 0; i < numLibs && allLibraries.size() > 0; i++) {
            int index = (int) (Math.random() * allLibraries.size());
            newLibs.add(allLibraries.remove(index));
        }
        allLibraries = newLibs;
    }
    public static List<Library> generateSchedule(int daysLeft, List<Library> schedule) {
        //maxVal is score/signUpTime
        if (daysLeft <= 0 || allLibraries.size() == 0) {
            return schedule;
        }

        //Library maxLib = maxScoreOverSetUp(daysLeft, scannedBooks);
        Library maxLib = opCostAlgo(daysLeft);
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
        return generateSchedule(daysLeft, schedule);
    }

    public static List<Library> generateScheduleIteratively() {
        int daysLeft = totalDays;
        List<Library> schedule = new ArrayList<>();
        while (daysLeft > 0 && allLibraries.size() > 0) {
            //Library maxLib = maxScoreOverSetUp(daysLeft, scannedBooks);
            Library maxLib = opCostAlgo(daysLeft);
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
        }
        return schedule;
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

    public static Library opCostAlgo (int daysLeft) {
        Library minLib = allLibraries.get(0);
        int minOpCost = getOpCostSum(allLibraries.get(0));
        for (Library lib : allLibraries) {
            if (lib.signUpTime > daysLeft) {
                continue; //ignore this library
            }
            int libOpCost = getOpCostSum(lib);
            if (libOpCost < minOpCost) {
                minLib = lib;
                minOpCost = libOpCost;
            }
        }
        return minLib;
    }

    /**
     * [getMaxOpCost description]
     * @param  lib [description]
     * @return     [description]
     */
    private static int getOpCostSum(Library lib) {
        int opCostSum = 0;
        for (Library curr : allLibraries) {
            if (curr == lib) {
                continue; //skip itself
            }
            opCostSum += curr.getOpCost(lib, scannedBooks);
        }
        return opCostSum;
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
