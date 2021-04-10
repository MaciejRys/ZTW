package com.lab4.book_app.services;

import com.lab4.book_app.model.Author;
import com.lab4.book_app.model.Book;
import com.lab4.book_app.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UsersService implements IUsersService{

    private static List<User> usersRepo = new ArrayList<>();


    @Override
    public boolean addUser(String username) {
        int id = usersRepo.size() + 1;

        User foundUser = usersRepo.stream()
                .filter(a -> a.getFullName() == username)
                .findAny()
                .orElse(null);

        User user = new User(id, username);
        //if book has unique id
        if(foundUser == null) {
            usersRepo.add(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User getItem(int id) {
        return usersRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElse(null);
    }


    @Override
    public Collection<User> getItems() {
        return usersRepo;
    }



}
