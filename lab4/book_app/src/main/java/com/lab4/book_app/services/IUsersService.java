package com.lab4.book_app.services;

import com.lab4.book_app.model.Author;
import com.lab4.book_app.model.User;

import java.util.Collection;

public interface IUsersService {

    boolean addUser(String username);

    public abstract User getItem(int id);

    public abstract Collection<User> getItems();



}
