package a.albertocoscia.entities;

import com.github.javafaker.Faker;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Supplier;

public abstract class WrittenMedium {
    protected String isbn;
    protected String title;
    protected LocalDate publicationDate;
    protected int numPages;

    Supplier<String> isbnSupplier = () -> {
        Faker faker = new Faker();
        return "978-0-" + faker.number().numberBetween(10, 99) + "-" + faker.number().numberBetween(10000, 99999) + "-" + faker.number().numberBetween(0, 9);
    };

    Supplier<LocalDate> pubDateSupplier = () -> {
        Faker faker = new Faker();
        LocalDate startDate = LocalDate.parse("1900-01-01");
        LocalDate today = LocalDate.now();
        return asLocalDate(faker.date().between(asDate(startDate), asDate(today)));
    };

    Supplier<Integer> numPagesSupplier = () -> {
        Faker faker = new Faker();
        return faker.number().numberBetween(30, 100);
    };

    public WrittenMedium(String title) {
        this.isbn = isbnSupplier.get();
        this.title = title;
        this.publicationDate = pubDateSupplier.get();
        this.numPages = numPagesSupplier.get();
    }

    protected static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    protected static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    @Override
    public String toString() {
        return "WrittenMedia{" +
                "numPages=" + numPages +
                ", publicationDate=" + publicationDate +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
