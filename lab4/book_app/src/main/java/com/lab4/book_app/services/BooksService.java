package com.lab4.book_app.services;

import com.lab4.book_app.model.Author;
import com.lab4.book_app.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class BooksService implements IBooksService {
    private static List<Book> booksRepo = new ArrayList<>();

    @Autowired
    private AuthorsService authorsService;

    @Override
    public boolean addItem(Book item) {
        Book foundBook = booksRepo.stream()
                .filter(a -> a.getId() == item.getId())
                .findAny()
                .orElse(null);

        //if book has unique id
        if(foundBook == null) {
            booksRepo.add(item);
            //if new author, create it
            if(authorsService.getItem(item.getAuthor().getId()) == null) {
                authorsService.addItem(item.getAuthor());
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Collection<Book> getItems() {
        return booksRepo;
    }

    @Override
    public Book getItem(int id) {
        return booksRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public boolean deleteItem(int id) {
        Book foundBook = booksRepo.stream()
                .filter(a -> a.getId() == id)
                .findAny()
                .orElse(null);

        if(foundBook == null) {
            return false;
        } else {
            booksRepo.remove(foundBook);
            return true;
        }
    }

    @Override
    public boolean updateItem(int id, Book updated) {
        Book foundBook = booksRepo.stream()
                .filter(a -> a.getId() == id)
                .findAny()
                .orElse(null);

        if(foundBook == null) {
            return false;
        } else {
            int index = booksRepo.indexOf(foundBook);
            booksRepo.set(index, updated);

            //if updated has new author, create it
            if(authorsService.getItem(updated.getAuthor().getId()) == null) {
                authorsService.addItem(updated.getAuthor());
            }
            return true;
        }
    }

    public static List<Book> getBooksRepo() {
        return booksRepo;
    }

    public static void setBooksRepo(List<Book> booksRepo) {
        BooksService.booksRepo = booksRepo;
    }
}
