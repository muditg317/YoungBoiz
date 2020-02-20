public class Book implements Comparable<Book>{
    private int score;
    private int id;

    public Book(int score, int id) {
        this.id = id;
        this.score = score;
    }

    public int compareTo(Book other) {
        return this.score - other.getScore();
    }

    public int getScore() {
        return score;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return id + ": " + score;
    }

    public int hashCode() {
        return id;
    }
}
