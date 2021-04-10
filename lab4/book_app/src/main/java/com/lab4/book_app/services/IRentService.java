package com.lab4.book_app.services;

import com.lab4.book_app.model.Book;
import com.lab4.book_app.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IRentService {

    Map<String, ArrayList<Book>> getRents();

    boolean rent(String user, Book book);

    boolean free(Book book);

    boolean isRented(Book book);
}
