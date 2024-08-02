package a.albertocoscia;

import a.albertocoscia.entities.Book;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        Faker faker = new Faker();

        List<Book> booksList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 50; i++) {
            // creo un nuovo libro
            Book newBook = new Book(faker.book().title(), faker.book().author(), faker.book().genre());

            /* cerco tra i libri gia presenti in booksList se ne esiste gia uno con lo stesso isbn del nuovo libro
            se l'isbn non esiste, aggiungo il libro a books list, altrimenti decremento i per non perdere un'iterazione
            e generare un nuovo libro */
            List<String> isbnAlreadySaved = booksList.stream()
                    .map(Book::getIsbn)
                    .filter(s -> s.equals(newBook.getIsbn()))
                    .toList();
            if (isbnAlreadySaved.isEmpty()) {
                newBook.addToList(booksList);
            } else {
                i--;
            }
        }
        for (Book book : booksList) {
            System.out.println(book);
        }

        /*System.out.println("Insert ISBN of the book you want to delete");
        String isbnToDelete = scanner.nextLine();
        List<Book> bookToRemove = booksList.stream().filter(book -> book.getIsbn().equals(isbnToDelete)).toList();
        if (!bookToRemove.isEmpty()) {
            booksList.remove(bookToRemove.getFirst());
            System.out.println("Book " + isbnToDelete + " successfully deleted");
            for (Book book : booksList) {
                System.out.println(book);
            }
        } else {
            System.err.println("Could not find ISBN: " + isbnToDelete);
        }*/

        /*System.out.println("Insert ISBN of the book you are searching for");
        String isbnToSearch = scanner.nextLine();
        Optional<Book> searchedBook = booksList.stream()
                .filter(book -> book.getIsbn().equals(isbnToSearch))
                .findFirst();
        if (searchedBook.isEmpty()) {
            System.err.println("Could not find ISBN: " + isbnToSearch);
        } else {
            System.out.println(searchedBook);
        }*/

        /*System.out.println("Type the year you want to search for");
        int yearToSearch = Integer.parseInt(scanner.nextLine());
        Map<Integer, List<Book>> booksBySearchedYear = booksList.stream()
                .filter(book -> book.getPublicationDate().getYear() == yearToSearch)
                .collect(Collectors.groupingBy(
                        book -> book.getPublicationDate().getYear()
                ));
        if (!booksBySearchedYear.isEmpty()) {
            booksBySearchedYear.forEach((year, books) -> System.out.println(year + ": " + books));
        } else {
            System.err.println("There are no books published in the year " + yearToSearch);
        }*/

        System.out.println("Type the author you want to search for");
        String authorToSearch = scanner.nextLine();

        Map<String, List<Book>> booksBySearchedAuthor = booksList.stream()
                .filter(book -> book.getAuthor().equals(authorToSearch))
                .collect(Collectors.groupingBy(
                        Book::getAuthor
                ));
        if (!booksBySearchedAuthor.isEmpty()) {
            booksBySearchedAuthor.forEach((author, books) -> System.out.println(author + ": " + books));
        } else {
            System.err.println("There are no books by the author " + authorToSearch);
        }
    }
}
