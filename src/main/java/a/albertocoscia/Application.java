package a.albertocoscia;

import a.albertocoscia.entities.Book;
import com.github.javafaker.Faker;

public class Application {

    public static void main(String[] args) {
        Faker faker = new Faker();

        Book book1 = new Book(faker.book().title(), faker.book().author(), faker.book().genre());
        System.out.println(book1);
    }
}
