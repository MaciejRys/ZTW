package com.lab4.book_app.services;

import com.lab4.book_app.model.Book;
import com.lab4.book_app.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService implements IUsersService{

    private static List<User> usersRepo = new ArrayList<>();


    @Override
    public boolean addUser(User user) {
        User foundUser = usersRepo.stream()
                .filter(a -> a.getId() == user.getId())
                .findAny()
                .orElse(null);

        //if book has unique id
        if(foundUser == null) {
            usersRepo.add(user);
            return true;
        } else {
            return false;
        }
    }
}
