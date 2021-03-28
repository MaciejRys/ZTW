package com.lab4.book_app.controllers;

import com.lab4.book_app.model.Book;
import com.lab4.book_app.model.User;
import com.lab4.book_app.services.IRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rents")
public class RentsController {

    @Autowired
    IRentService rentService;

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<Object> getRents() {
        return new ResponseEntity<>(rentService.getRents(), HttpStatus.OK);
    }

    @RequestMapping(value = "/rent", method = RequestMethod.POST)
    ResponseEntity<Object> rent(@RequestBody User user, @RequestBody Book book) {
        return new ResponseEntity<>(rentService.rent(user, book), HttpStatus.OK);
    }

    @RequestMapping(value = "/free", method = RequestMethod.POST)
    ResponseEntity<Object> free(@RequestBody Book book) {
        return new ResponseEntity<>(rentService.free(book), HttpStatus.OK);
    }
}
