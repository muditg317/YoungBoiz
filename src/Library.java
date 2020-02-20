import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class Library {

    public ArrayList<Book> books;
    public int signUpTime;
    public int booksPerDay;

    public Library(int signUpTime, int booksPerDay) {
        this.signUpTime = signUpTime;
        this.booksPerDay = booksPerDay;
        books = new ArrayList();
    }
    public void addBook(Book book) {
        books.add(book);
    }

    public void sort() {
        Collections.sort(books);
        Collections.reverse(books);
    }

    public String toString() {
        String returnS = "";
        returnS += ("SignUp: " + signUpTime + "\n");
        returnS += ("BooksPerDay: " + booksPerDay + "\n");
        for(Book book: books) {
            returnS += (book + " ");
        }
        return returnS;
    }

    public int score(int daysLeft, Set<Integer> scannedBooks) {
        daysLeft -= signUpTime;
        int score = 0;
        for (int day = 0; day < daysLeft; day++) {
            int book = 0;
            for (int i = 0; i < booksPerDay; i++) {
                if (!scannedBooks.contains(books.get(i))) {
                    score += books.get(book).getScore();
                } else {
                    i--; //since we skipped a book
                }
                book++;
            }
        }
        return score;
    }
}
