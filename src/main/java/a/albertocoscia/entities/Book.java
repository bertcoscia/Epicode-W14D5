package a.albertocoscia.entities;

import java.util.List;

public class Book extends WrittenMedia {
    protected String author;
    protected String genre;

    public Book(String title, String author, String genre) {
        super(title);
        this.author = author;
        this.genre = genre;
    }

    public void addToList(List<Book> booksList) {
        booksList.add(this);
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
