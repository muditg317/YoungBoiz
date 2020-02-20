public class Book implements Comparable<Book>{
    private int score;

    public Book(int score) {
        this.score = score;
    }

    public int compareTo(Book other) {
        return this.score - other.getScore();
    }

    public int getScore() {
        return score;
    }

    public String toString() {
        return((Integer.toString(score)));
    }
}
