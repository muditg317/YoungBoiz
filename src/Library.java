import java.util.ArrayList;
import java.util.Collections;

public class Library {

    public ArrayList<Book> books;
    public int signUp;
    public int booksPerDay;

    public Library(int signUp, int booksPerDay) {
        this.signUp = signUp;
        this.booksPerDay = booksPerDay;
        books = new ArrayList();
    }
    public void addBook(Book book) {
        books.add(book);
    }

    public void sort() {
        Collections.sort(books);
    }

    public String toString() {
        String returnS = "";
        returnS += ("SignUp: " + signUp + "\n");
        returnS += ("BooksPerDay: " + booksPerDay + "\n");
        for(Book book: books) {
            returnS += (book + " ");
        }
        return returnS;
    }
}
