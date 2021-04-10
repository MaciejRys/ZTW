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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rents")
public class RentsController {

    @Autowired
    IRentService rentService;
    @Autowired
    IBooksService booksService;
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<Object> getRents() {
        return new ResponseEntity<>(rentService.getRents(), HttpStatus.OK);
    }
    @CrossOrigin
    @RequestMapping(value = "/rent/{Book}/{User}", method = RequestMethod.POST)
    public ResponseEntity<Object> rent(@PathVariable("Book") String Book, @PathVariable("User") String User){
        Book book = booksService.getItemByName(Book);
        return new ResponseEntity<>(rentService.rent(User, book), HttpStatus.OK);
    }
    @CrossOrigin
    @RequestMapping(value = "/free/{Book}", method = RequestMethod.POST)
    ResponseEntity<Object> free(@PathVariable("Book") String Book) {
        Book book = booksService.getItemByName(Book);
        return new ResponseEntity<>(rentService.free(book), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/freeBooks", method = RequestMethod.GET)
    ResponseEntity<Object> freeBooks() {

        List<Book> booksForRent = new ArrayList<>();
        for (Book book: booksService.getItems()) {
            if(!rentService.isRented(book)){
                booksForRent.add(book);
            }
        }
        return new ResponseEntity<>(booksForRent, HttpStatus.OK);
    }
}
