package com.lab4.book_app.services;

import com.lab4.book_app.model.Book;
import com.lab4.book_app.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IRentService {

    Map<User, ArrayList<Book>> getRents();

    boolean rent(User user, Book book);

    boolean free(Book book);
}
