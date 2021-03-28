package com.lab4.book_app.services;

import com.lab4.book_app.model.Author;
import com.lab4.book_app.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AuthorsService implements IAuthorsService {

    private static List<Author> authorsRepo = new ArrayList<>();

    @Autowired
    private BooksService booksService;

    @Override
    public boolean addItem(Author item) {
        Author foundAuthor = authorsRepo.stream()
                .filter(a -> a.getId() == item.getId())
                .findAny()
                .orElse(null);

        if(foundAuthor == null) {
            authorsRepo.add(item);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Collection<Author> getItems() {
        return authorsRepo;
    }

    @Override
    public Author getItem(int id) {
        return authorsRepo.stream()
                .filter(a -> a.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public boolean deleteItem(int id) {
        Author foundAuthor = authorsRepo.stream()
                .filter(a -> a.getId() == id)
                .findAny()
                .orElse(null);

        if(foundAuthor == null) {
            return false;
        } else {
            authorsRepo.remove(foundAuthor);
            //delete all books written by author
            booksService.getItems().stream()
                    .filter(b -> b.getAuthor().equals(foundAuthor))
                    .forEach(b -> booksService.deleteItem(b.getId()));
            return true;
        }
    }

    @Override
    public boolean updateItem(int id, Author updated) {
        Author foundAuthor = authorsRepo.stream()
                .filter(a -> a.getId() == id)
                .findAny()
                .orElse(null);

        if(foundAuthor == null) {
            return false;
        } else {
            int index = authorsRepo.indexOf(foundAuthor);
            authorsRepo.set(index, updated);
            //update author for each books
            booksService.getItems().stream()
                    .filter(b -> b.getAuthor().equals(foundAuthor))
                    .forEach(b -> b.setAuthor(updated));
            return true;
        }
    }

    public static List<Author> getAuthorsRepo() {
        return authorsRepo;
    }

    public static void setAuthorsRepo(List<Author> authorsRepo) {
        AuthorsService.authorsRepo = authorsRepo;
    }


}
