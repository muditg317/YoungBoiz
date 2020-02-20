import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

public class Library {

    public ArrayList<Book> books;
    public int signUpTime;
    public int booksPerDay;
    public int id;
    public Set<Book> bookSet;

    public Library(int signUpTime, int booksPerDay, int id) {
        this.signUpTime = signUpTime;
        this.booksPerDay = booksPerDay;
        this.id = id;
        books = new ArrayList();
    }
    public void addBook(Book book) {
        books.add(book);
    }

    public void sort() {
        Collections.sort(books);
        Collections.reverse(books);
        bookSet = new HashSet<>(books);
    }


    public String toString() {
        String returnS = "";
        returnS += ("SignUp: " + signUpTime + "\n");
        returnS += ("BooksPerDay: " + booksPerDay + "\n");
        for(Book book : books) {
            returnS += (book + " ");
        }
        return returnS;
    }

    /**
     * [score description]
     * @param  daysLeft     [description]
     * @param  scannedBooks [description]
     * @return              [description]
     */
    public int score(int daysLeft, Set<Book> scannedBooks) {
        // System.out.println("scoring: " + id);
        // System.out.println("books:"+books);
        // System.out.println("scanned:"+scannedBooks);
        daysLeft -= signUpTime;
        int score = 0;
        int book = 0;
        for (int day = 0; day < daysLeft; day++) {
            for (int i = 0; i < booksPerDay && book < books.size(); i++) {
                if (!scannedBooks.contains(books.get(book))) {
                    score += books.get(book).getScore();
                } else {
                    i--; //since we skipped a book
                }
                book++;
            }
        }
        // System.out.println("score:"+score);
        return score;
    }
    /**
     * Calculates the op cost of choosing lib other
     * @param  other [description]
     * @return       [description]
     */
    public int getOpCost(Library other, Set<Book> scannedBooks) {
        int days = other.signUpTime;
        int opCost = 0;
        int book = books.size() - 1;
        for (int i = 0; i < days * booksPerDay && book >= 0; i++) {
            Book b = books.get(book);
            if (other.bookSet.contains(b) || scannedBooks.contains(b)) {
                i--; //skip this book
            } else {
                opCost += b.getScore();
            }
            book++;
        }
        return opCost;
    }
    /**
     * Adds the books this will scan to the scannedBook set and
     * @param daysLeft     [description]
     * @param scannedBooks [description]
     */
    public void publish(int daysLeft, Set<Book> scannedBooks) {

        daysLeft -= signUpTime;
        ArrayList<Book> finalBooks = new ArrayList<>(books.size());
        int score = 0;
        int book = 0;
        for (int day = 0; day < daysLeft; day++) {
            for (int i = 0; i < booksPerDay && book < books.size(); i++) {
                if (scannedBooks.add(books.get(book))) {
                    finalBooks.add(books.get(book));
                    score += books.get(book).getScore();
                } else {
                    i--; //since we skipped a book
                }
                book++;
            }
        }
        books = finalBooks;
        // System.out.println("published "+id+":"+books);
    }

}
