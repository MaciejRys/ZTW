package com.lab4.book_app.services;

import com.lab4.book_app.model.Book;
import com.lab4.book_app.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RentService implements IRentService{

    private Map<User, ArrayList<Book>> rents;

    public RentService() {
        this.rents = new HashMap<>();
    }

    @Override
    public Map<User, ArrayList<Book>> getRents() {
        return rents;
    }

    @Override
    public boolean rent(User user, Book book) {
        if(isRented(book)) {
            return false;
        } else {
            if(rents.keySet().contains(user)) {
                rents.get(user).add(book);
            } else {
                ArrayList<Book> userBooks = new ArrayList<>();
                userBooks.add(book);
                rents.put(user, userBooks);
            }
            return true;
        }
    }

    @Override
    public boolean free(Book book) {
        if(!isRented(book)) {
            return false;
        } else {
            rents.values().stream()
                    .forEach(bl -> {
                        if(bl.contains(book)) {
                            bl.remove(book);
                            return;
                        }
                    });
            return true;
        }
    }

    private boolean isRented(Book book) {
        Book foundBook = rents.values().stream()
                .reduce(new ArrayList<Book>(), (acc, item) -> {
            acc.addAll(item);
            return acc;
        })
                .stream()
                .filter(b -> b.getId() == book.getId()).findAny().orElse(null);

        return foundBook != null;
    }
}
