package a.albertocoscia.entities;

public class Book extends WrittenMedium {
    protected String author;
    protected String genre;

    public Book(String title, String author, String genre) {
        super(title);
        this.author = author;
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", numPages=" + numPages +
                ", publicationDate=" + publicationDate +
                '}';
    }
}
