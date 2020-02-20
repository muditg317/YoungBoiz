import java.util.ArrayList;

public class Registration {
    public int id;
    public ArrayList<Book> order;

    public Registration(int id, ArrayList<Book> order) {
        this.id = id;
        this.order = order;
    }
}