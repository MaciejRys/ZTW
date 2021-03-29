package com.lab4.book_app.controllers;

import com.lab4.book_app.model.Book;
import com.lab4.book_app.model.User;
import com.lab4.book_app.services.IBooksService;
import com.lab4.book_app.services.IRentService;
import com.lab4.book_app.services.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rents")
public class RentsController {

    @Autowired
    IRentService rentService;
    @Autowired
    IBooksService booksService;
    @Autowired
    IUsersService usersService;

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<Object> getRents() {
        return new ResponseEntity<>(rentService.getRents(), HttpStatus.OK);
    }

    @RequestMapping(value = "/rent/{idBook}/{idUser}", method = RequestMethod.POST)
    public ResponseEntity<Object> rent(@PathVariable("idBook") int idBook, @PathVariable("idUser") int idUser){
        Book book = booksService.getItem(idBook);
        User user = usersService.getItem(idUser);
        return new ResponseEntity<>(rentService.rent(user, book), HttpStatus.OK);
    }

    @RequestMapping(value = "/free", method = RequestMethod.POST)
    ResponseEntity<Object> free(@RequestBody Book book) {
        return new ResponseEntity<>(rentService.free(book), HttpStatus.OK);
    }
}
