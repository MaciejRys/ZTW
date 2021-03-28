package com.lab4.book_app.controllers;

import com.lab4.book_app.model.Book;
import com.lab4.book_app.services.IBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
public class BooksController {
    @Autowired
    IBooksService booksService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> addBook(@RequestBody Book book){
        boolean result = booksService.addItem(book);

        if(result) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getBooks(){
        return new ResponseEntity<>(booksService.getItems(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getBook(@PathVariable("id") int id){
        Book book = booksService.getItem(id);

        if(book == null) {
            return new ResponseEntity<>(book, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBook(@PathVariable("id") int id){
        return new ResponseEntity<>(booksService.deleteItem(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Object> updateBook(@PathVariable("id") int id, @RequestBody Book book){
        return new ResponseEntity<>(booksService.updateItem(id, book), HttpStatus.OK);
    }
}
