package a.albertocoscia;

import a.albertocoscia.entities.Book;
import a.albertocoscia.entities.Magazine;
import a.albertocoscia.entities.WrittenMedium;
import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
        for (int i = 0; i < 2; i++) {
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
        for (int i = 0; i < 2; i++) {
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
        for (int i = 0; i < 2; i++) {
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
        System.out.println("5. Search for works by year");
        System.out.println("6. Search for a book by author");
        System.out.println("0. Exit");

        String action;
        do {
            String input = scanner.nextLine();
            action = input;
            switch (input) {
                case "1":
                    System.out.println("Type the title of the book");
                    String newBookTitle = scanner.nextLine();
                    System.out.println("Type the author of the book");
                    String newBookAuthor = scanner.nextLine();
                    System.out.println("Type the genre of the book");
                    String newBookGenre = scanner.nextLine();
                    try {
                        booksList.add(new Book(newBookTitle, newBookAuthor, newBookGenre));
                        for (Book book : booksList) {
                            System.out.println(book);
                        }
                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                    }
                    System.out.println("What do you want to do? Type the correspondent number");
                    System.out.println("1. Create a new book");
                    System.out.println("2. Create a new magazine");
                    System.out.println("3. Remove a book or a magazine by ISBN");
                    System.out.println("4. Search for a book or a magazine by ISBN");
                    System.out.println("5. Search for works by year");
                    System.out.println("6. Search for a book by author");
                    System.out.println("0. Exit");
                    break;
                case "2":
                    System.out.println("Type the title of the magazine");
                    String newMagazineTitle = scanner.nextLine();
                    System.out.println("Choose the periodicity of the magazine: Weekly, Monthly, Semiannual");
                    String newMagazinePeriodicity = scanner.nextLine().toLowerCase();
                    switch (newMagazinePeriodicity) {
                        case "weekly":
                            try {
                                magazinesList.add(new Magazine(newMagazineTitle, Periodicity.WEEKLY));
                                for (Magazine magazine : magazinesList) {
                                    System.out.println(magazine);
                                }
                            } catch (IllegalArgumentException e) {
                                System.err.println(e.getMessage());
                            }
                            break;
                        case "monthly":
                            try {
                                magazinesList.add(new Magazine(newMagazineTitle, Periodicity.MONTHLY));
                                for (Magazine magazine : magazinesList) {
                                    System.out.println(magazine);
                                }
                            } catch (IllegalArgumentException e) {
                                System.err.println(e.getMessage());
                            }
                            break;
                        case "semiannual":
                            try {
                                magazinesList.add(new Magazine(newMagazineTitle, Periodicity.SEMIANNUAL));
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
                    System.out.println("What do you want to do? Type the correspondent number");
                    System.out.println("1. Create a new book");
                    System.out.println("2. Create a new magazine");
                    System.out.println("3. Remove a book or a magazine by ISBN");
                    System.out.println("4. Search for a book or a magazine by ISBN");
                    System.out.println("5. Search for works by year");
                    System.out.println("6. Search for a book by author");
                    System.out.println("0. Exit");
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
                    System.out.println("What do you want to do? Type the correspondent number");
                    System.out.println("1. Create a new book");
                    System.out.println("2. Create a new magazine");
                    System.out.println("3. Remove a book or a magazine by ISBN");
                    System.out.println("4. Search for a book or a magazine by ISBN");
                    System.out.println("5. Search for works by year");
                    System.out.println("6. Search for a book by author");
                    System.out.println("0. Exit");
                    break;
                case "4":
                    List<WrittenMedium> writtenMediaListIsbn = new ArrayList<>();
                    writtenMediaListIsbn.addAll(booksList);
                    writtenMediaListIsbn.addAll(magazinesList);
                    for (WrittenMedium medium : writtenMediaListIsbn) {
                        System.out.println(medium);
                    }
                    System.out.println("Insert ISBN you are searching for");
                    String isbnToSearch = scanner.nextLine();
                    Optional<WrittenMedium> searchedMedium = writtenMediaListIsbn.stream()
                            .filter(writtenMedium -> writtenMedium.getIsbn().equals(isbnToSearch)).findFirst();
                    if (searchedMedium.isEmpty()) {
                        System.err.println("Could not find ISBN: " + isbnToSearch);
                    } else {
                        System.out.println(searchedMedium.get());
                    }
                    System.out.println("What do you want to do? Type the correspondent number");
                    System.out.println("1. Create a new book");
                    System.out.println("2. Create a new magazine");
                    System.out.println("3. Remove a book or a magazine by ISBN");
                    System.out.println("4. Search for a book or a magazine by ISBN");
                    System.out.println("5. Search for works by year");
                    System.out.println("6. Search for a book by author");
                    System.out.println("0. Exit");
                    break;
                case "5":
                    List<WrittenMedium> writtenMediaListYear = new ArrayList<>();
                    writtenMediaListYear.addAll(booksList);
                    writtenMediaListYear.addAll(magazinesList);
                    for (WrittenMedium medium : writtenMediaListYear) {
                        System.out.println(medium);
                    }
                    System.out.println("Insert the year you want to filter results by");
                    int yearToSearch;
                    try {
                        yearToSearch = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid year format. Please enter a valid number.");
                        return;
                    }
                    Map<Integer, List<WrittenMedium>> writtenMediaBySearchedYear = writtenMediaListYear.stream()
                            .filter(writtenMedium -> writtenMedium.getPublicationDate().getYear() == yearToSearch)
                            .collect(Collectors.groupingBy(
                                    writtenMedium -> writtenMedium.getPublicationDate().getYear()
                            ));
                    if (!writtenMediaBySearchedYear.isEmpty()) {
                        writtenMediaBySearchedYear.forEach((year, medium) -> System.out.println(year + ": " + medium));
                    } else {
                        System.err.println("There are no works published in the year " + yearToSearch);
                    }
                    System.out.println("What do you want to do? Type the correspondent number");
                    System.out.println("1. Create a new book");
                    System.out.println("2. Create a new magazine");
                    System.out.println("3. Remove a book or a magazine by ISBN");
                    System.out.println("4. Search for a book or a magazine by ISBN");
                    System.out.println("5. Search for works by year");
                    System.out.println("6. Search for a book by author");
                    System.out.println("0. Exit");
                    break;
                case "6":
                    for (Book book : booksList) {
                        System.out.println(book);
                    }
                    System.out.println("Type the author you want to search for");
                    String authorToSearch = scanner.nextLine().toLowerCase();
                    Map<String, List<Book>> booksBySearchedAuthor = booksList.stream()
                            .filter(book -> book.getAuthor().toLowerCase().equals(authorToSearch))
                            .collect(Collectors.groupingBy(
                                    Book::getAuthor
                            ));
                    if (!booksBySearchedAuthor.isEmpty()) {
                        booksBySearchedAuthor.forEach((author, books) -> System.out.println(author + ": " + books));
                    } else {
                        System.err.println("There are no books by the author " + authorToSearch);
                    }
                    System.out.println("What do you want to do? Type the correspondent number");
                    System.out.println("1. Create a new book");
                    System.out.println("2. Create a new magazine");
                    System.out.println("3. Remove a book or a magazine by ISBN");
                    System.out.println("4. Search for a book or a magazine by ISBN");
                    System.out.println("5. Search for works by year");
                    System.out.println("6. Search for a book by author");
                    System.out.println("0. Exit");
                    break;
                default:
                    if (!action.equals("0")) {
                        System.out.println("Choose a valid option");
                        System.out.println("1. Create a new book");
                        System.out.println("2. Create a new magazine");
                        System.out.println("3. Remove a book or a magazine by ISBN");
                        System.out.println("4. Search for a book or a magazine by ISBN");
                        System.out.println("5. Search for works by year");
                        System.out.println("6. Search for a book by author");
                        System.out.println("0. Exit");
                        break;
                    } else {
                        break;
                    }
            }
        } while (!action.equals("0"));

        File archiveFile = new File("src/archive.txt");
        try {
            saveArchiveToDisk(booksList, magazinesList, archiveFile);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        List<WrittenMedium> writtenMediaList = new ArrayList<>();
        try {
            createListFromFile(archiveFile, writtenMediaList);
            for (WrittenMedium medium : writtenMediaList) {
                System.out.println(medium);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    public static void saveArchiveToDisk(List<Book> booksList, List<Magazine> magazinesList, File file) throws IOException {
        StringBuilder stringa = new StringBuilder();
        for (Book book : booksList) {
            stringa.append(book.getIsbn()).append("@").append(book.getTitle()).append("@").append(book.getAuthor()).append("@").append(book.getGenre()).append("@").append(book.getNumPages()).append("@").append(book.getPublicationDate()).append(System.lineSeparator());
        }
        for (Magazine magazine : magazinesList) {
            stringa.append(magazine.getTitle()).append("@").append(magazine.getPeriodicity()).append("@").append(magazine.getIsbn()).append("@").append(magazine.getPublicationDate()).append("@").append(magazine.getNumPages()).append(System.lineSeparator());
        }
        FileUtils.writeStringToFile(file, stringa.toString(), StandardCharsets.UTF_8);
        System.out.println("Archive successfully saved");
    }

    public static void createListFromFile(File file, List<WrittenMedium> writtenMediaList) throws IOException {
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        String[] contentAsArray = content.split(System.lineSeparator());
        for (String string : contentAsArray) {
            String[] mediumAsString = string.split("@");
            if (mediumAsString.length > 5) {
                Book book = new Book(mediumAsString[1], mediumAsString[2], mediumAsString[3]);
                writtenMediaList.add(book);
            } else {
                String newMagazinePeriodicity = mediumAsString[1].toLowerCase();
                switch (newMagazinePeriodicity) {
                    case "weekly":
                        Magazine newWeeklyMagazine = new Magazine(mediumAsString[0], Periodicity.WEEKLY);
                        writtenMediaList.add(newWeeklyMagazine);
                        break;
                    case "monthly":
                        Magazine newMonthlyMagazine = new Magazine(mediumAsString[0], Periodicity.MONTHLY);
                        writtenMediaList.add(newMonthlyMagazine);
                        break;
                    case "semiannual":
                        Magazine newSemiannualMagazine = new Magazine(mediumAsString[0], Periodicity.SEMIANNUAL);
                        writtenMediaList.add(newSemiannualMagazine);
                        break;
                }
            }
        }
    }
}
