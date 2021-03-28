package com.lab4.book_app;

import com.lab4.book_app.model.Author;
import com.lab4.book_app.model.Book;
import com.lab4.book_app.model.User;
import com.lab4.book_app.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MockData implements ApplicationRunner {

    @Autowired
    IAuthorsService authorsService;
    @Autowired
    IBooksService booksService;
    @Autowired
    IUsersService usersService;
    @Autowired
    IRentService rentService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Author author1 = new Author(1, "Jan Kochanowski");
        Author author2 = new Author(2, "Adam Mickiewicz");

        Book book1 = new Book(1, "Dziady", author2, 576);
        Book book2 = new Book(2, "Pan Tadeusz", author2, 1246);
        Book book3 = new Book(3, "Fraszki", author1, 234);

        User user1 = new User(1, "Jan Kowalski");
        User user2 = new User(2, "Adam Dąbrowski");

        authorsService.getItems().add(author1);
        authorsService.getItems().add(author2);

        booksService.getItems().add(book1);
        booksService.getItems().add(book2);
        booksService.getItems().add(book3);

        usersService.addUser(user1);
        usersService.addUser(user2);

        rentService.rent(user1, book2);
    }
}
