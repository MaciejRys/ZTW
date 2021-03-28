package com.lab4.book_app.services;

import com.lab4.book_app.model.Author;
import com.lab4.book_app.model.Book;

import java.util.Collection;

public interface IAuthorsService {

    public abstract boolean addItem(Author item);

    public abstract Collection<Author> getItems();

    public abstract Author getItem(int id);

    public abstract boolean deleteItem(int id);

    public abstract boolean updateItem(int id, Author updated);
}
