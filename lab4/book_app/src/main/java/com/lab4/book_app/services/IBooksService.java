package com.lab4.book_app.services;

import com.lab4.book_app.model.Book;

import java.util.Collection;

public interface IBooksService {
    public abstract boolean addItem(Book item);

    public abstract Collection<Book> getItems();

    public abstract Book getItem(int id);

    public abstract boolean deleteItem(int id);

    public abstract boolean updateItem(int id, Book updated);
}
