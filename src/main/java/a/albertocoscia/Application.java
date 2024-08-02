package a.albertocoscia;

import a.albertocoscia.entities.Book;
import a.albertocoscia.entities.Magazine;
import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        Faker faker = new Faker();

        List<Book> booksList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            // creo un nuovo libro
            Book newBook = new Book(faker.book().title(), faker.book().author(), faker.book().genre());

            /* cerco tra i libri gia presenti in booksList se ne esiste gia uno con lo stesso isbn del nuovo libro
            se l'isbn non esiste, aggiungo il libro a books list, altrimenti decremento i per non perdere un'iterazione
            e generare un nuovo libro */
            Optional<Book> isbnAlreadySaved = booksList.stream()
                    .filter(book -> book.getIsbn().equals(newBook.getIsbn()))
                    .findFirst();
            if (isbnAlreadySaved.isEmpty()) {
                booksList.add(newBook);
            } else {
                i--;
            }
        }
        /*for (Book book : booksList) {
            System.out.println(book);
        }*/

        List<Magazine> magazinesList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Magazine newMagazine = new Magazine(faker.medical().diseaseName(), Periodicity.WEEKLY);
            Optional<Magazine> isbnAlreadySaved = magazinesList.stream()
                    .filter(magazine -> magazine.getIsbn().equals(newMagazine.getIsbn()))
                    .findFirst();
            if (isbnAlreadySaved.isEmpty()) {
                magazinesList.add(newMagazine);
            } else {
                i--;
            }
        }
        for (int i = 0; i < 5; i++) {
            Magazine newMagazine = new Magazine(faker.app().name(), Periodicity.MONTHLY);
            Optional<Magazine> isbnAlreadySaved = magazinesList.stream()
                    .filter(magazine -> magazine.getIsbn().equals(newMagazine.getIsbn()))
                    .findFirst();
            if (isbnAlreadySaved.isEmpty()) {
                magazinesList.add(newMagazine);
            } else {
                i--;
            }
        }
        for (int i = 0; i < 5; i++) {
            Magazine newMagazine = new Magazine(faker.university().name(), Periodicity.SEMIANNUAL);
            Optional<Magazine> isbnAlreadySaved = magazinesList.stream()
                    .filter(magazine -> magazine.getIsbn().equals(newMagazine.getIsbn()))
                    .findFirst();
            if (isbnAlreadySaved.isEmpty()) {
                magazinesList.add(newMagazine);
            } else {
                i--;
            }
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("What do you want to do? Type the correspondent number");
        System.out.println("1. Create a new book");
        System.out.println("2. Create a new magazine");
        System.out.println("3. Remove a book or a magazine by ISBN");
        System.out.println("4. Search for a book or a magazine by ISBN");
        System.out.println("5. Search for a book or a magazine by year");
        System.out.println("6. Search for a book or a magazine by author");
        System.out.println("0. Exit");

        String action = "";
        do {
            String input = scanner.nextLine();
            action = input;
            switch (input) {
                case "1":
                    System.out.println("Type the title of the book");
                    String bookTitle = scanner.nextLine();
                    System.out.println("Type the author of the book");
                    String author = scanner.nextLine();
                    System.out.println("Type the genre of the book");
                    String genre = scanner.nextLine();
                    try {
                        booksList.add(new Book(bookTitle, author, genre));
                        for (Book book : booksList) {
                            System.out.println(book);
                        }
                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case "2":
                    System.out.println("Type the title of the magazine");
                    String magazineTitle = scanner.nextLine();
                    System.out.println("Choose the periodicity of the magazine: Weekly, Monthly, Semiannual");
                    String periodicity = scanner.nextLine().toLowerCase();
                    switch (periodicity) {
                        case "weekly":
                            try {
                                magazinesList.add(new Magazine(magazineTitle, Periodicity.WEEKLY));
                                for (Magazine magazine : magazinesList) {
                                    System.out.println(magazine);
                                }
                            } catch (IllegalArgumentException e) {
                                System.err.println(e.getMessage());
                            }
                            break;
                        case "monthly":
                            try {
                                magazinesList.add(new Magazine(magazineTitle, Periodicity.MONTHLY));
                                for (Magazine magazine : magazinesList) {
                                    System.out.println(magazine);
                                }
                            } catch (IllegalArgumentException e) {
                                System.err.println(e.getMessage());
                            }
                            break;
                        case "semiannual":
                            try {
                                magazinesList.add(new Magazine(magazineTitle, Periodicity.SEMIANNUAL));
                                for (Magazine magazine : magazinesList) {
                                    System.out.println(magazine);
                                }
                            } catch (IllegalArgumentException e) {
                                System.err.println(e.getMessage());
                            }
                            break;
                        default:
                            System.err.println("Choose one between Weekly, Monthly, Semiannual");
                    }
                    break;
                case "3":
                    System.out.println("Do you want to delete a book or a magazine?");
                    String typeToDelete = scanner.nextLine().toLowerCase();
                    switch (typeToDelete) {
                        case "book":
                            for (Book book : booksList) {
                                System.out.println(book);
                            }
                            System.out.println("Insert ISBN of the book to delete");
                            String isbnBookToDelete = scanner.nextLine();
                            Optional<Book> bookToDelete = booksList.stream().filter(book -> book.getIsbn().equals(isbnBookToDelete)).findFirst();
                            if (bookToDelete.isPresent()) {
                                booksList.remove(bookToDelete.get());
                                System.out.println("Book " + isbnBookToDelete + " successfully deleted");
                                for (Book book : booksList) {
                                    System.out.println(book);
                                }
                            } else {
                                System.err.println("Could not find ISBN: " + isbnBookToDelete);
                            }
                            break;
                        case "magazine":
                            for (Magazine magazine : magazinesList) {
                                System.out.println(magazine);
                            }
                            System.out.println("Insert ISBN of the magazine to delete");
                            String isbnMagazineToDelete = scanner.nextLine();
                            Optional<Magazine> magazineToDelete = magazinesList.stream().filter(magazine -> magazine.getIsbn().equals(isbnMagazineToDelete)).findFirst();
                            if (magazineToDelete.isPresent()) {
                                magazinesList.remove(magazineToDelete.get());
                                System.out.println("Magazine " + isbnMagazineToDelete + " successfully deleted");
                                for (Magazine magazine : magazinesList) {
                                    System.out.println(magazine);
                                }
                            } else {
                                System.err.println("Could not find ISBN: " + isbnMagazineToDelete);
                            }
                            break;
                        default:
                            System.err.println("Choose one between book and magazine");
                    }
                    break;
                case "4":
                    System.out.println("Do you want to search for a book or a magazine?");
                    String typeToSearch = scanner.nextLine().toLowerCase();
                    switch (typeToSearch) {
                        case "book":
                            for (Book book : booksList) {
                                System.out.println(book);
                            }
                            System.out.println("Insert ISBN of the book you are searching for");
                            String isbnBookToSearch = scanner.nextLine();
                            Optional<Book> searchedBook = booksList.stream()
                                    .filter(book -> book.getIsbn().equals(isbnBookToSearch))
                                    .findFirst();
                            if (searchedBook.isEmpty()) {
                                System.err.println("Could not find ISBN: " + isbnBookToSearch);
                            } else {
                                System.out.println(searchedBook);
                            }
                            break;
                        case "magazine":
                            for (Magazine magazine : magazinesList) {
                                System.out.println(magazine);
                            }
                            System.out.println("Insert ISBN of the magazine you are searching for");
                            String isbnMagazineToSearch = scanner.nextLine();
                            Optional<Magazine> searchedMagazine = magazinesList.stream()
                                    .filter(magazine -> magazine.getIsbn().equals(isbnMagazineToSearch))
                                    .findFirst();
                            if (searchedMagazine.isEmpty()) {
                                System.err.println("Could not find ISBN: " + isbnMagazineToSearch);
                            } else {
                                System.out.println(searchedMagazine);
                            }
                            break;
                        default:
                            System.err.println("Choose one between book and magazine");
                    }
            }

        } while (!action.equals("0"));

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
